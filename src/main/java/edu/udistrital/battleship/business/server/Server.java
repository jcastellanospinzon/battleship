package edu.udistrital.battleship.business.server;

import edu.udistrital.battleship.business.protocol.AttackResponse;
import edu.udistrital.battleship.business.protocol.Message;
import edu.udistrital.battleship.business.protocol.Protocol;
import edu.udistrital.battleship.business.protocol.Response;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.udistrital.battleship.business.protocol.Protocol.buildMessage;

public class Server implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);

    private boolean running;

    private int port;

    private ServerClient homePlayer;

    private ServerClient guestPlayer;

    private boolean homePlayerReady;

    private boolean guestPlayerReady;

    private boolean waitingAttackResponse;

    private ServerClient playerTurn;

    public Server() {
        running = false;
        homePlayerReady = false;
        guestPlayerReady = false;
        waitingAttackResponse = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void startServer(int port) {
        LOGGER.info("Starting server in port: {}", port);
        this.port = port;
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stopServer() {
        running = false;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("Server Socket has Started succesfully");
            homePlayer = playerConnection(serverSocket);
            guestPlayer = playerConnection(serverSocket);
        } catch (IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
            throw new Error("Server Connection Error", e);
        }
    }

    private ServerClient playerConnection(ServerSocket serverSocket) {
        LOGGER.info("Waiting for player connection...");
        try {
            Socket clientSocket = serverSocket.accept();
            ServerClient serverClient = new ServerClient(this);
            serverClient.startServerClient(clientSocket);
            return serverClient;
        } catch (IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
            throw new AssertionError("Player Connection Error", e);
        }
    }

    public Message connectionMessage(ServerClient serverClient, Message message) {
        return buildMessage()
                   .withResponse(Response.OKAY)
                   .withName(message.getName());
    }

    public Message readyMessage(ServerClient serverClient, Message message) {
        if (Objects.equals(serverClient, homePlayer)) {
            homePlayerReady = true;
        } else {
            guestPlayerReady = true;
        }
        if (homePlayerReady && guestPlayerReady) {
            LOGGER.debug("Both players are ready!");
            playerTurn = homePlayer;
        }
        return buildMessage()
                   .withResponse(Response.OKAY);
    }

    public void attackMessage(ServerClient serverClient, Message message) {
        if (homePlayerReady && guestPlayerReady && !waitingAttackResponse && Objects.equals(serverClient, playerTurn)) {
            waitingAttackResponse = true;
            getRivalFor(serverClient).sendMessage(message);
        } else {
            serverClient.sendMessage(Protocol.buildMessage()
                                         .withResponse(Response.NOT_OKAY));
        }
    }

    private ServerClient getRivalFor(ServerClient serverClient) {
        return Objects.equals(serverClient, homePlayer) ? guestPlayer : homePlayer;
    }

    public void attackResponse(ServerClient serverClient, Message message) {
        if (homePlayerReady && guestPlayerReady && waitingAttackResponse && !Objects.equals(serverClient, playerTurn)) {
            getRivalFor(serverClient).sendMessage(message);
            if (message.getAttackResponse() == AttackResponse.NO_IMPACT) {
                playerTurn = serverClient;
            }
            waitingAttackResponse = false;
        } else {
            serverClient.sendMessage(Protocol.buildMessage()
                                         .withResponse(Response.NOT_OKAY));
        }
    }

}
