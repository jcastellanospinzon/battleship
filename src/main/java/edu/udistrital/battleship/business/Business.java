package edu.udistrital.battleship.business;

import edu.udistrital.battleship.business.client.Client;
import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Shot;
import edu.udistrital.battleship.business.game.Shot.Result;
import edu.udistrital.battleship.business.protocol.AttackResponse;
import edu.udistrital.battleship.business.protocol.Command;
import edu.udistrital.battleship.business.protocol.Protocol;
import edu.udistrital.battleship.business.protocol.Response;
import edu.udistrital.battleship.business.server.Server;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.nonNull;

public class Business {

    private static final Logger LOGGER = LogManager.getLogger(Business.class);

    private List<BusinessObserver> observers;

    private Server server;

    private Client client;

    private Board playerBoard;

    private Board rivalBoard;

    private boolean gameFinished;

    private boolean playerWin;

    private boolean rivalWin;

    public Business() {
        observers = new ArrayList<>();
        gameFinished = false;
    }

    public void init() {

    }

    public void registerObserver(BusinessObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(BusinessObserver::notifyChange);
    }

    public void startServer(int port) {
        LOGGER.debug("Starting server...");
        server = new Server();
        server.startServer(port);
    }

    public void stopServer() {

    }

    public boolean isServerRunning() {
        return nonNull(server) && server.isRunning();
    }

    public void startClient(String playerName, String hostname, int port) {
        LOGGER.debug("Starting client...");
        client = new Client(this);
        client.startClient(playerName, hostname, port);
    }

    public void stopClient() {

    }

    public boolean isClientRunning() {
        return nonNull(client) && client.isRunning();
    }

    public void playGame(Board playerBoard, Board rivalBoard) {
        LOGGER.debug("Play Game!");
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

    public void doShot(Point point) {
        LOGGER.info("Shooting at point: {}", point);
        client.sendMessage(Protocol.buildMessage().withCommand(Command.ATTACK).withPoint(point));
    }

    public void doShotResponse(Point point, AttackResponse attackResponse) {
        LOGGER.info("Receiving shot response at point: {}", point);
        Shot.Result shotResult = attackResponse == AttackResponse.NO_IMPACT ? Result.MISS : Result.SUCCESS;
        rivalBoard.addShot(new Shot(shotResult, point));
        if (rivalBoard.allShipsSunken()) {
            LOGGER.info("All rival ships are sunken. You Win!");
            gameFinished = true;
            playerWin = true;
        } else if (shotResult == Result.SUCCESS) {
            LOGGER.info("You impact a ship!");
        } else {
            LOGGER.info("You miss!");
        }
        notifyObservers();
    }

    public void receiveShot(Point point) {
        LOGGER.info("Receiving shot at point: {}", point);
        boolean impact = playerBoard.thereIsAnyShip(point);
        Shot.Result shotResult = impact ? Result.SUCCESS : Result.MISS;
        playerBoard.addShot(new Shot(shotResult, point));
        if (playerBoard.allShipsSunken()) {
            LOGGER.info("All your ships are sunken. You Lose!");
            client.sendMessage(Protocol.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.WIN));
            gameFinished = true;
            rivalWin = true;
        } else if (impact) {
            LOGGER.info("Your ship was impacted!");
            client.sendMessage(Protocol.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.IMPACT));
        } else {
            LOGGER.info("Your rival missed!");
            client.sendMessage(Protocol.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.NO_IMPACT));
        }
        notifyObservers();
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public boolean isPlayerWin() {
        return playerWin;
    }

    public boolean isRivalWin() {
        return rivalWin;
    }

}
