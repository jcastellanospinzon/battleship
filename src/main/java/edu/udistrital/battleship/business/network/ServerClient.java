package edu.udistrital.battleship.business.network;

import edu.udistrital.battleship.business.protocol.Command;
import edu.udistrital.battleship.business.protocol.Message;
import edu.udistrital.battleship.business.protocol.Response;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.nonNull;

public class ServerClient
    extends AbstractClient {

    private static final Logger LOGGER = LogManager.getLogger(ServerClient.class);

    private final Server server;

    private Socket clientSocket;

    public ServerClient(Server server) {
        super();
        this.server = server;
    }

    public void startServerClient(Socket clientSocket) {
        LOGGER.info("Player {} has been connected succesfully", clientSocket.getInetAddress());
        this.clientSocket = clientSocket;
        startThreadClient();
    }

    public void stopServerClient() {
        running = false;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream())) {
            LOGGER.info("Client Thread has Started succesfully");
            setDataOutputStream(dataOutputStream);

            while (running) {
                String strReceivedMessage = dataInputStream.readUTF();
                LOGGER.debug("Server Client received Message {}", strReceivedMessage);
                translateMessage(strReceivedMessage);
            }
        } catch (IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
        }
    }

    protected void processMessage(Message message) {
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

}
