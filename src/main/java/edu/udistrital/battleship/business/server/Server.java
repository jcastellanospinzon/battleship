package edu.udistrital.battleship.business.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable {

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


        } catch (IOException e) {

        }
    }

}
