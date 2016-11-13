package edu.udistrital.battleship.business;

import edu.udistrital.battleship.business.client.Client;
import edu.udistrital.battleship.business.server.Server;

import static java.util.Objects.nonNull;

public class Business {

    private Server server;

    private Client client;

    public Business() {
    }

    public void init() {

    }

    public void startServer() {

    }

    public void stopServer() {

    }

    public boolean isServerRunning() {
        return nonNull(server) && server.isRunning();
    }

    public void startClient() {

    }

    public void stopClient() {

    }

    public boolean isClientRunning() {
        return nonNull(client) && client.isRunning();
    }

}
