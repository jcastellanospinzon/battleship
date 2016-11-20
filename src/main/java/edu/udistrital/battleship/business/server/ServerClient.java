package edu.udistrital.battleship.business.server;

import edu.udistrital.battleship.business.protocol.Command;
import edu.udistrital.battleship.business.protocol.InvalidMessageException;
import edu.udistrital.battleship.business.protocol.Message;
import edu.udistrital.battleship.business.protocol.Protocol;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerClient implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(ServerClient.class);

    private final Server server;

    private boolean running;

    private Socket clientSocket;

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

                Message message = Protocol.getMessageFromString(strReceivedMessage, null);
                Message responseMessage = processMessage(message);

                LOGGER.debug("Server Client writing Message {}", responseMessage.getString());
                dataOutputStream.writeUTF(responseMessage.getString());
                dataOutputStream.flush();
            }
        } catch (InvalidMessageException | IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
            throw new AssertionError("Player Connection Error", e);
        }
    }

    private void referenceOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    private Message processMessage(Message message) {
        if (message.getCommand() == Command.CONNECT) {
            return server.connectionMessage(this, message);
        }
        if(message.getCommand() == Command.READY) {
            return server.readyMessage(this, message);
        }
        if(message.getCommand() == Command.ATTACK) {
            return server.attackMessage(this, message);
        }
        return null;
    }

    public void sendMessage(Message message) {
        try {
            String strConnectionMessage = message.getString();

            LOGGER.debug("Client writing Message {}", strConnectionMessage);
            dataOutputStream.writeUTF(strConnectionMessage);
            dataOutputStream.flush();
        } catch (IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
            throw new AssertionError("Player Connection Error", e);
        }
    }

}
