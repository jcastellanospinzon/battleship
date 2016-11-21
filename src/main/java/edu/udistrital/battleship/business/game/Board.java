package edu.udistrital.battleship.business.game;

import edu.udistrital.battleship.business.game.Shot.Result;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.nonNull;

public class Board {

    private static final Logger LOGGER = LogManager.getLogger(Board.class);

    private static final int BOARD_SIZE = 10;

    private static final int SHIPS_COUNT = 10;

    private static final int LIFE_COUNT = 20;

    private List<Ship> ships;

    private List<Shot> shots;

    private Ship[][] shipsCells;

    private Shot[][] shotsCells;

    public Board() {
        ships = new ArrayList<>();
        shipsCells = new Ship[BOARD_SIZE][BOARD_SIZE];
        shots = new ArrayList<>();
        shotsCells = new Shot[BOARD_SIZE][BOARD_SIZE];
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
        ship.getPoints().forEach(point -> shipsCells[point.getRow().getArrayPosition()][point.getColumn().getArrayPosition()] = ship);
    }

    public boolean shipFits(Point point, Ship.Type shipType, Ship.Orientation shipOrientation) {
        try {
            new Ship(shipType, shipOrientation, point);
            return true;
        } catch (ShipDoesNotFitException e) {
            LOGGER.error("Ship of type {} and orientation {} cannot be located at {}", shipType, shipOrientation, point, e);
            return false;
        }
    }

    public boolean thereIsShip(Point point, Ship.Type shipType, Ship.Orientation shipOrientation) {
        try {
            Ship temporalShip = new Ship(shipType, shipOrientation, point);
            return temporalShip.getPoints().stream()
                       .anyMatch(temporalPoint -> nonNull(shipsCells[temporalPoint.getRow().getArrayPosition()][temporalPoint.getColumn().getArrayPosition()]));
        } catch (ShipDoesNotFitException e) {
            LOGGER.error("Ship of type {} and orientation {} cannot be located at {}", shipType, shipOrientation, point, e);
            return false;
        }
    }

    public boolean thereIsAnyShip(Point point) {
        return nonNull(shipsCells[point.getRow().getArrayPosition()][point.getColumn().getArrayPosition()]);
    }

    public void addShot(Shot shot) {
        shots.add(shot);
        shotsCells[shot.getPoint().getRow().getArrayPosition()][shot.getPoint().getColumn().getArrayPosition()] = shot;
    }

    public boolean thereIsShot(Point point) {
        return nonNull(shotsCells[point.getRow().getArrayPosition()][point.getColumn().getArrayPosition()]);
    }

    public boolean allShipsAllocated() {
        return ships.size() == SHIPS_COUNT;
    }

    public boolean allShipsSunken() {
        long shotCount = shots.stream().filter(shot -> shot.getResult() == Result.SUCCESS).count();
        return shotCount == LIFE_COUNT;
    }

}
