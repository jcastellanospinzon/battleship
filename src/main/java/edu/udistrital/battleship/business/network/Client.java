package edu.udistrital.battleship.business.network;

import edu.udistrital.battleship.business.Business;
import edu.udistrital.battleship.business.protocol.Command;
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

public class Client
    extends AbstractClient {

    private static final Logger LOGGER = LogManager.getLogger(Client.class);

    private final Business business;

    private String playerName;

    private String hostname;

    private int port;

    public Client(Business business) {
        super();
        this.business = business;
    }

    public void startClient(String playerName, String hostname, int port) {
        LOGGER.info("Starting client with name: {} in hostname: {} and port: {}", playerName, hostname, port);
        this.playerName = playerName;
        this.hostname = hostname;
        this.port = port;
        startThreadClient();
    }

    public void stopClient() {
        running = false;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(hostname, port);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            LOGGER.info("Client Socket has started succesfully");
            setDataOutputStream(dataOutputStream);

            sendMessage(Protocol.buildMessage()
                            .withCommand(Command.CONNECT)
                            .withName(playerName));

            while (running) {
                String strReceivedMessage = dataInputStream.readUTF();
                LOGGER.debug("Client received Message {}", strReceivedMessage);
                translateMessage(strReceivedMessage);
            }
        } catch (IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
        }
    }

    protected void processMessage(Message message) {
        if (message.getCommand() == Command.ATTACK) {
            business.receiveShot(message.getPoint());
        }
        if (message.getResponse() == Response.OKAY && nonNull(lastMessage) && lastMessage.getCommand() == Command.ATTACK) {
            business.doShotResponse(lastMessage.getPoint(), message.getAttackResponse());
        }
        lastMessage = null;
    }

}
