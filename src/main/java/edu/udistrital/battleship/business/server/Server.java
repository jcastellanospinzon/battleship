package edu.udistrital.battleship.business.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);

    private boolean running;

    private int port;

    private Thread thread;

    public Server() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void startServer(int port) {
        LOGGER.info("Starting server in port: {}", port);
        this.port = port;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stopServer() {
        running = false;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.info("Server Socket has Started succesfully");
            playerConnection(serverSocket, "Home");
            playerConnection(serverSocket, "Guest");
        } catch (IOException e) {

        }
    }

    private void playerConnection(ServerSocket serverSocket, String playerName) {
        LOGGER.info("Waiting for player \"{}\" connection", playerName);
        try (Socket clientSocket = serverSocket.accept()) {
            LOGGER.info("Player {} has been connected succesfully", playerName);
            ServerClient serverClient = new ServerClient();
            serverClient.startServerClient(clientSocket);

        } catch (IOException e) {

        }
    }

}
