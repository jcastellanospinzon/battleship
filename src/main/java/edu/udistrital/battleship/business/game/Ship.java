package edu.udistrital.battleship.business.game;

public class Ship {

    private final Type type;

    private final Point point;

    private int life;

    public Ship(Type type, Point point) {
        this.type = type;
        this.point = point;
        life = type.length;
    }

    public Type getType() {
        return type;
    }

    public Point getPoint() {
        return point;
    }

    public int getLife() {
        return life;
    }

    enum Type {

        BOAT(1, "boat-icon-40.png", "boat-icon-40.png");

        private final int length;

        private final String verticalIconPath;

        private final String horizontalIconPath;

        Type(int length, String verticalIconPath, String horizontalIconPath) {
            this.length = length;
            this.verticalIconPath = verticalIconPath;
            this.horizontalIconPath = horizontalIconPath;
        }

        public int getLength() {
            return length;
        }

        public String getVerticalIconPath() {
            return verticalIconPath;
        }

        public String getHorizontalIconPath() {
            return horizontalIconPath;
        }

    }

}
