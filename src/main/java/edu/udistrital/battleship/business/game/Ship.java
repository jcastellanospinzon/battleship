package edu.udistrital.battleship.business.game;

import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import java.util.ArrayList;
import java.util.List;

public class Ship {

    private final Type type;

    private final Orientation orientation;

    private final Point point;

    private int life;

    public Ship(Type type, Orientation orientation, Point point) {
        this.type = type;
        this.orientation = orientation;
        this.point = point;
        life = type.length;
    }

    public Type getType() {
        return type;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point getPoint() {
        return point;
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        Point currentPoint = point;
        int sizeCounter = 1;
        do {
            points.add(point);
            if (orientation == Orientation.VERTICAL) {
                int newRowArrayPosition = currentPoint.getRow().getArrayPosition() + 1;
                currentPoint = new Point(Row.fromArrayPosition(newRowArrayPosition).get(), currentPoint.getColumn());
            } else {
                int newColumnArrayPosition = currentPoint.getColumn().getArrayPosition() + 1;
                currentPoint = new Point(currentPoint.getRow(), Column.fromArrayPosition(newColumnArrayPosition).get());
            }
            sizeCounter++;
        } while (sizeCounter <= type.getLength());
        return points;
    }

    public int getLife() {
        return life;
    }

    public enum Orientation {

        VERTICAL,

        HORIZONTAL;

    }

    public enum Type {

        CARRIER(4, 1),

        BATTLESHIP(3, 2),

        CRUISER(2, 3),

        SUBMARINE(1, 4);

        private final int length;

        private final int itemsPerBoard;

        Type(int length, int itemsPerBoard) {
            this.length = length;
            this.itemsPerBoard = itemsPerBoard;
        }

        public int getLength() {
            return length;
        }

        public int getItemsPerBoard() {
            return itemsPerBoard;
        }

    }

}
