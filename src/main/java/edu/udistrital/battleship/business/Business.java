package edu.udistrital.battleship.business;

import edu.udistrital.battleship.business.client.Client;
import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.protocol.Command;
import edu.udistrital.battleship.business.protocol.Protocol;
import edu.udistrital.battleship.business.server.Server;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class Business {

    private List<BusinessObserver> observers;

    private Server server;

    private Client client;

    private Board playerBoard;

    private Board rivalBoard;

    public Business() {
        observers = new ArrayList<>();
    }

    public void init() {

    }

    public void registerObserver(BusinessObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(observer -> observer.notifyChange());
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

    public void startClient(String playerName, String hostname, int port) {
        client = new Client();
        client.startClient(playerName, hostname, port);
    }

    public void stopClient() {

    }

    public boolean isClientRunning() {
        return nonNull(client) && client.isRunning();
    }

    public void playGame(Board playerBoard, Board rivalBoard) {
        this.playerBoard = playerBoard;
        this.rivalBoard = rivalBoard;
        client.sendMessage(Protocol.buildMessage().withCommand(Command.READY));
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getRivalBoard() {
        return rivalBoard;
    }
}
