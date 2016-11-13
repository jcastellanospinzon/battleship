package edu.udistrital.battleship.business.client;

import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Client.class);

    private boolean running;

    private String hostname;

    private int port;

    private Thread thread;

    public Client() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void startClient(String hostname, int port) {
        LOGGER.info("Starting client in hostname: {} and port: {}", hostname, port);
        this.hostname = hostname;
        this.port = port;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stopClient() {
        running = false;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(hostname, port)) {
            LOGGER.info("Client Socket has started succesfully");

        } catch (IOException e) {

        }
    }

}
