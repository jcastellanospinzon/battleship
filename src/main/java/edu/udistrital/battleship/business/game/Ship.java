package edu.udistrital.battleship.business.game;

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
            if(orientation == Orientation.VERTICAL) {
                // Calcular currentPoint incrementando la fila
            } else {
                // Calcular currentPoint incrementando la columna
            }
            sizeCounter++;
        } while (sizeCounter < type.getLength());
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

        CARRIER(4, 1, "boat-icon-40.png", "boat-icon-40.png"),

        BATTLESHIP(3, 2, "boat-icon-40.png", "boat-icon-40.png"),

        CRUISER(2, 3, "boat-icon-40.png", "boat-icon-40.png"),

        SUBMARINE(1, 4, "boat-icon-40.png", "boat-icon-40.png");

        private final int length;

        private final int itemsPerBoard;

        private final String verticalIconPath;

        private final String horizontalIconPath;

        Type(int length, int itemsPerBoard, String verticalIconPath, String horizontalIconPath) {
            this.length = length;
            this.itemsPerBoard = itemsPerBoard;
            this.verticalIconPath = verticalIconPath;
            this.horizontalIconPath = horizontalIconPath;
        }

        public int getLength() {
            return length;
        }

        public int getItemsPerBoard() {
            return itemsPerBoard;
        }

        public String getVerticalIconPath() {
            return verticalIconPath;
        }

        public String getHorizontalIconPath() {
            return horizontalIconPath;
        }

    }

}
