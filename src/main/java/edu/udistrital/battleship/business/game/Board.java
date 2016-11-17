package edu.udistrital.battleship.business.game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class Board {

    private List<Ship> ships;

    private Ship[][] cells;

    public Board() {
        ships = new ArrayList<>();
        cells = new Ship[10][10];
    }

    public void addShip(Ship ship) {
    }

    public boolean isEmpty(Point point) {
        return isNull(cells[point.getRow().getArrayPosition()][point.getColumn().getArrayPosition()]);
    }

}
