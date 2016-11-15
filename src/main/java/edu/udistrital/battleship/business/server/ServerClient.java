package edu.udistrital.battleship.business.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerClient implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(Server.class);

    private boolean running;

    private Socket clientSocket;

    private Thread thread;

    public ServerClient() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void startServerClient(Socket clientSocket) {
        LOGGER.info("Starting client thread in server: {}", clientSocket.getInetAddress());
        this.clientSocket = clientSocket;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stopServerClient() {
        running = false;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            LOGGER.info("Client Thread has Started succesfully");





        } catch (IOException e) {

        }
    }
}
