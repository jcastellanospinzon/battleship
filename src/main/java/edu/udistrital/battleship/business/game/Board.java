package edu.udistrital.battleship.business.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class Board {

    private List<Ship> ships;

    private Boolean[][] shotsCells;

    private Ship[][] shipsCells;

    public Board() {
        ships = new ArrayList<>();
        shipsCells = new Ship[10][10];
        shotsCells = new Boolean[10][10];
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
        ship.getPoints().forEach(point -> shipsCells[point.getRow().getArrayPosition()][point.getColumn().getArrayPosition()] = ship);
    }

    public boolean thereIsShip(Point point, Ship.Type shipType, Ship.Orientation shipOrientation) {
        Ship temporalShip = new Ship(shipType, shipOrientation, point);
        return temporalShip.getPoints().stream()
                .allMatch(temporalPoint -> isNull(shipsCells[temporalPoint.getRow().getArrayPosition()][temporalPoint.getColumn().getArrayPosition()]));
    }

}
