package edu.udistrital.battleship.business;

import edu.udistrital.battleship.business.client.Client;
import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.server.Server;

import static java.util.Objects.nonNull;

public class Business {

    private Server server;

    private Client client;

    private Board playerBoard;

    public Business() {
    }

    public void init() {

    }

    public void startServer(int port) {
        server = new Server();
        server.startServer(port);
    }

    public void stopServer() {

    }

    public boolean isServerRunning() {
        return nonNull(server) && server.isRunning();
    }

    public void startClient(String hostname, int port) {
        client = new Client();
        client.startClient(hostname, port);
    }

    public void stopClient() {

    }

    public boolean isClientRunning() {
        return nonNull(client) && client.isRunning();
    }

    public void playGame(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

}
