package edu.udistrital.battleship.business.game;

import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ship {

    private final Type type;

    private final Orientation orientation;

    private final Point referencePoint;

    private final List<Point> points;

    private int life;

    public Ship(Type type, Orientation orientation, Point referencePoint)
        throws ShipDoesNotFitException {
        this.type = type;
        this.orientation = orientation;
        this.referencePoint = referencePoint;
        this.points = Collections.unmodifiableList(calculatePoints(type, orientation, referencePoint));
        life = type.length;
    }

    public Type getType() {
        return type;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Point getReferencePoint() {
        return referencePoint;
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getLife() {
        return life;
    }

    private static List<Point> calculatePoints(Type type, Orientation orientation, Point referencePoint)
        throws ShipDoesNotFitException {
        List<Point> points = new ArrayList<>();
        Point currentPoint = referencePoint;
        while (points.size() < type.getLength()) {
            points.add(currentPoint);
            if (points.size() < type.getLength()) {
                currentPoint = (orientation == Orientation.VERTICAL) ? getPointBelow(currentPoint) : getPointAtRight(currentPoint);
            }
        }
        return points;
    }

    private static Point getPointAtRight(Point point)
        throws ShipDoesNotFitException {
        int newColumnArrayPosition = point.getColumn().getArrayPosition() + 1;
        Row currentPointRow = point.getRow();
        Column currentPointColumn = Column.fromArrayPosition(newColumnArrayPosition)
                                        .orElseThrow(() -> new ShipDoesNotFitException("Ship cannot be localized at column " + newColumnArrayPosition));
        return new Point(currentPointRow, currentPointColumn);
    }

    private static Point getPointBelow(Point point)
        throws ShipDoesNotFitException {
        int newRowArrayPosition = point.getRow().getArrayPosition() + 1;
        Column currentPointColumn = point.getColumn();
        Row currentPointRow = Row.fromArrayPosition(newRowArrayPosition)
                                  .orElseThrow(() -> new ShipDoesNotFitException("Ship cannot be localized at row " + newRowArrayPosition));
        return new Point(currentPointRow, currentPointColumn);
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
