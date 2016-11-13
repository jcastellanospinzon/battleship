package edu.udistrital.battleship.business.client;

public class Client implements Runnable {

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


    }

    public void stopClient() {
        running = false;
    }

    @Override
    public void run() {

    }

}
