package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.business.game.Ship.Orientation;
import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.util.Objects.nonNull;

public class ControllerLoadPlayer extends Controller<ModelLoadPlayer, ViewLoadPlayer>
        implements ActionListener, MouseListener, MouseMotionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnPlay())) {
            play();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(view.getCanvasLoadPlayer()) &&
                e.getButton() == MouseEvent.BUTTON1) {
            click(e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void play() {

    }

    private void click(int x, int y) {
        Point point = initPoint(x, y);
        if (nonNull(point) && boardIsEmpty(point)) {
            String selectedShip = (String) view.getCmbShips().getSelectedItem();
            Ship.Type type = view.getShipTypes().get(selectedShip);
            Ship.Orientation orientation = view.getBtnVerticalShip().isSelected() ? Orientation.VERTICAL : Orientation.HORIZONTAL;
            model.addShip(type, orientation, point);
        }
    }

    private boolean boardIsEmpty(Point point) {
        return model.getBoard().isEmpty(point);
    }


    private Point initPoint(int x, int y) {
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
            return new Point(row, column);
        } else {
            return null;
        }

    }

}
