package edu.udistrital.battleship.business.game;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.isNull;

public class Board {

    private static final Logger LOGGER = LogManager.getLogger(Board.class);

    private List<Ship> ships;

    private List<Shot> shots;

    private Ship[][] shipsCells;

    private Shot[][] shotsCells;

    public Board() {
        ships = new ArrayList<>();
        shipsCells = new Ship[10][10];
        shots = new ArrayList<>();
        shotsCells = new Shot[10][10];
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
                       .allMatch(temporalPoint -> isNull(shipsCells[temporalPoint.getRow().getArrayPosition()][temporalPoint.getColumn().getArrayPosition()]));
        } catch (ShipDoesNotFitException e) {
            LOGGER.error("Ship of type {} and orientation {} cannot be located at {}", shipType, shipOrientation, point, e);
            return false;
        }
    }

}
