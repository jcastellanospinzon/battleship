package edu.udistrital.battleship.business.server;

import edu.udistrital.battleship.business.protocol.Command;
import edu.udistrital.battleship.business.protocol.InvalidMessageException;
import edu.udistrital.battleship.business.protocol.Message;
import edu.udistrital.battleship.business.protocol.Protocol;
import edu.udistrital.battleship.business.protocol.Response;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.nonNull;

public class ServerClient implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(ServerClient.class);

    private final Server server;

    private boolean running;

    private Socket clientSocket;

    private Message lastMessage;

    private DataOutputStream dataOutputStream;

    public ServerClient(Server server) {
        this.server = server;
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void startServerClient(Socket clientSocket) {
        LOGGER.info("Player {} has been connected succesfully", clientSocket.getInetAddress());
        this.clientSocket = clientSocket;
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stopServerClient() {
        running = false;

    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())) {
            LOGGER.info("Client Thread has Started succesfully");
            referenceOutputStream(dataOutputStream);

            while (running) {
                String strReceivedMessage = dataInputStream.readUTF();
                LOGGER.debug("Server Client received Message {}", strReceivedMessage);

                Command lastMessageCommand = nonNull(lastMessage) ? lastMessage.getCommand() : null;
                Message message = Protocol.getMessageFromString(strReceivedMessage, lastMessageCommand);
                processMessage(message);
            }
        } catch (InvalidMessageException | IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
            throw new AssertionError("Player Connection Error", e);
        }
    }

    private void referenceOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    private void processMessage(Message message) {
        if (message.getCommand() == Command.CONNECT) {
            Message responseMessage = server.connectionMessage(this, message);
            sendMessage(responseMessage);
        }
        if (message.getCommand() == Command.READY) {
            Message responseMessage = server.readyMessage(this, message);
            sendMessage(responseMessage);
        }
        if (message.getCommand() == Command.ATTACK) {
            server.attackMessage(this, message);
        }
        if (message.getResponse() == Response.OKAY && nonNull(lastMessage) && lastMessage.getCommand() == Command.ATTACK) {
            server.attackResponse(this, message);
        }
        lastMessage = null;
    }

    public void sendMessage(Message message) {
        try {
            lastMessage = message;
            String strConnectionMessage = message.getString();

            LOGGER.debug("Server Client writing Message {}", strConnectionMessage);
            dataOutputStream.writeUTF(strConnectionMessage);
            dataOutputStream.flush();
        } catch (IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
            throw new AssertionError("Player Connection Error", e);
        }
    }

}
