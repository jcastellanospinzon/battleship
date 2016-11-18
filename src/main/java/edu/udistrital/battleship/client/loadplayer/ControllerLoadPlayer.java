package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.business.game.Ship.Orientation;
import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard.initPoint;
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
        String selectedShip = (String) view.getCmbShips().getSelectedItem();
        Ship.Type shipType = view.getShipTypes().get(selectedShip);
        Ship.Orientation shipOrientation = view.getBtnVerticalShip().isSelected() ? Orientation.VERTICAL : Orientation.HORIZONTAL;
        if (nonNull(point) && boardIsEmpty(point, shipType, shipOrientation) && shipFits(point, shipType, shipOrientation)) {
            model.addShip(shipType, shipOrientation, point);
        }
    }

    private boolean boardIsEmpty(Point point, Ship.Type shipType, Ship.Orientation shipOrientation) {
        return model.getBoard().thereIsShip(point, shipType, shipOrientation);
    }

    private boolean shipFits(Point point, Ship.Type shipType, Ship.Orientation shipOrientation) {
        return true;
    }

}
