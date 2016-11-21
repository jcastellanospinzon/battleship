package edu.udistrital.battleship.business.game;

public class Shot {

    private final Result result;

    private final Point point;

    public Shot(Result result, Point point) {
        this.result = result;
        this.point = point;
    }

    public Result getResult() {
        return result;
    }

    public Point getPoint() {
        return point;
    }

    public enum Result {

        SUCCESS,

        MISS

    }

}
