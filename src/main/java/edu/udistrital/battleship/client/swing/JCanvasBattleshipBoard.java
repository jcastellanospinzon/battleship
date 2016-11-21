package edu.udistrital.battleship.client.swing;

import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.business.game.Ship.Orientation;
import edu.udistrital.battleship.business.game.Ship.Type;
import edu.udistrital.battleship.business.game.Shot;
import edu.udistrital.battleship.business.game.Shot.Result;
import edu.udistrital.battleship.client.mvc.View;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import org.apache.commons.lang3.tuple.Pair;

import static java.util.Objects.nonNull;

public class JCanvasBattleshipBoard extends JComponent {

    private Board board;

    private Map<Integer, Integer> cellReferencePoint;

    private Map<Pair<Ship.Type, Ship.Orientation>, Image> shipImages;

    private Map<Shot.Result, Image> shotImages;

    public JCanvasBattleshipBoard() {
        super();

        cellReferencePoint = new HashMap<>();
        cellReferencePoint.put(0, 42);
        cellReferencePoint.put(1, 82);
        cellReferencePoint.put(2, 122);
        cellReferencePoint.put(3, 162);
        cellReferencePoint.put(4, 202);
        cellReferencePoint.put(5, 242);
        cellReferencePoint.put(6, 282);
        cellReferencePoint.put(7, 322);
        cellReferencePoint.put(8, 362);
        cellReferencePoint.put(9, 402);

        shipImages = new HashMap<>();
        shipImages.put(Pair.of(Type.CARRIER, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-carrierh-160.png")).getImage());
        shipImages.put(Pair.of(Type.CARRIER, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-carrierv-160.png")).getImage());
        shipImages.put(Pair.of(Type.BATTLESHIP, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-battleshiph-120.png")).getImage());
        shipImages.put(Pair.of(Type.BATTLESHIP, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-battleshipv-120.png")).getImage());
        shipImages.put(Pair.of(Type.CRUISER, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cruiserh-80.png")).getImage());
        shipImages.put(Pair.of(Type.CRUISER, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cruiserv-80.png")).getImage());
        shipImages.put(Pair.of(Type.SUBMARINE, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-submarineh-40.png")).getImage());
        shipImages.put(Pair.of(Type.SUBMARINE, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-submarinev-40.png")).getImage());

        shotImages = new HashMap<>();
        shotImages.put(Result.SUCCESS, new ImageIcon(View.class.getResource("/images/battleship-shoot_success-40.png")).getImage());
        shotImages.put(Result.MISS, new ImageIcon(View.class.getResource("/images/battleship-shoot_miss-40.png")).getImage());

        setSize(new Dimension(444, 444));
        setMinimumSize(new Dimension(444, 444));
        setMaximumSize(new Dimension(444, 444));
        setPreferredSize(new Dimension(444, 444));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(View.class.getResource("/images/battleship-table-444.png")).getImage(), 0, 0, this);
        if (nonNull(board)) {
            paintBoard(g);
        }
    }

    private void paintBoard(Graphics g) {
        paintShips(g);
        paintShots(g);
    }

    private void paintShips(Graphics g) {
        for (Ship ship : board.getShips()) {
            int cellReferencePointX = cellReferencePoint.get(ship.getReferencePoint().getColumn().getArrayPosition());
            int cellReferencePointY = cellReferencePoint.get(ship.getReferencePoint().getRow().getArrayPosition());
            Image shipImage = shipImages.get(Pair.of(ship.getType(), ship.getOrientation()));
            g.drawImage(shipImage, cellReferencePointX, cellReferencePointY, this);
        }
    }

    private void paintShots(Graphics g) {
        for(Shot shot : board.getShots()) {
            int cellReferencePointX = cellReferencePoint.get(shot.getPoint().getColumn().getArrayPosition());
            int cellReferencePointY = cellReferencePoint.get(shot.getPoint().getRow().getArrayPosition());
            Image shotImage = shotImages.get(shot.getResult());
            g.drawImage(shotImage, cellReferencePointX, cellReferencePointY, this);
        }
    }

    public void setBoard(Board board) {
        this.board = board;
        repaint();
    }

    public static Optional<Point> initPoint(int x, int y) {
        Point.Row row = null;
        Point.Column column = null;
        if (x > 42 && x <= 82) {
            column = Column.COLUMN_1;
        } else if (x > 82 && x <= 122) {
            column = Column.COLUMN_2;
        } else if (x > 122 && x <= 162) {
            column = Column.COLUMN_3;
        } else if (x > 162 && x <= 202) {
            column = Column.COLUMN_4;
        } else if (x > 202 && x <= 242) {
            column = Column.COLUMN_5;
        } else if (x > 242 && x <= 282) {
            column = Column.COLUMN_6;
        } else if (x > 282 && x <= 322) {
            column = Column.COLUMN_7;
        } else if (x > 322 && x <= 362) {
            column = Column.COLUMN_8;
        } else if (x > 362 && x <= 402) {
            column = Column.COLUMN_9;
        } else if (x > 402 && x <= 442) {
            column = Column.COLUMN_10;
        }
        if (y > 42 && y <= 82) {
            row = Row.ROW_1;
        } else if (y > 82 && y <= 122) {
            row = Row.ROW_2;
        } else if (y > 122 && y <= 162) {
            row = Row.ROW_3;
        } else if (y > 162 && y <= 202) {
            row = Row.ROW_4;
        } else if (y > 202 && y <= 242) {
            row = Row.ROW_5;
        } else if (y > 242 && y <= 282) {
            row = Row.ROW_6;
        } else if (y > 282 && y <= 322) {
            row = Row.ROW_7;
        } else if (y > 322 && y <= 362) {
            row = Row.ROW_8;
        } else if (y > 362 && y <= 402) {
            row = Row.ROW_9;
        } else if (y > 402 && y <= 442) {
            row = Row.ROW_10;
        }
        if (nonNull(row) && nonNull(column)) {
            return Optional.of(new Point(row, column));
        } else {
            return Optional.empty();
        }
    }

}
