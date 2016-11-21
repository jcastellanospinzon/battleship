package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.business.game.Ship.Orientation;
import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;

import static edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard.initPoint;

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
        if (e.getSource().equals(view.getCanvasLoadPlayer())) {
            mouseMovement(e.getX(), e.getY());
        }
    }

    private void play() {
        model.playGame();
    }

    private void click(int x, int y) {
        if (!allShipsAllocated()) {
            Optional<Point> optPoint = initPoint(x, y);
            String selectedShip = (String) view.getCmbShips().getSelectedItem();
            Ship.Type shipType = view.getShipTypes().get(selectedShip);
            Ship.Orientation shipOrientation = view.getBtnVerticalShip().isSelected() ? Orientation.VERTICAL : Orientation.HORIZONTAL;
            if (optPoint.isPresent() && shipFits(optPoint.get(), shipType, shipOrientation) && boardIsEmpty(optPoint.get(), shipType, shipOrientation)) {
                model.addShip(shipType, shipOrientation, optPoint.get());
            }
        }
    }

    private void mouseMovement(int x, int y) {
        Optional<Point> optPoint = initPoint(x, y);
        if (!allShipsAllocated() && optPoint.isPresent()) {
            String selectedShip = (String) view.getCmbShips().getSelectedItem();
            Ship.Type shipType = view.getShipTypes().get(selectedShip);
            Ship.Orientation shipOrientation = view.getBtnVerticalShip().isSelected() ? Orientation.VERTICAL : Orientation.HORIZONTAL;
            if (shipFits(optPoint.get(), shipType, shipOrientation) && boardIsEmpty(optPoint.get(), shipType, shipOrientation)) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = view.getCanvasLoadPlayer().getShipImages().get(Pair.of(shipType, shipOrientation));
                Cursor cursor = toolkit.createCustomCursor(image, new java.awt.Point(0, 0), "load-player-cursor"); // FIXME Mejorar el cursor
                view.getCanvasLoadPlayer().setCursor(cursor);
            } else {
                view.getCanvasLoadPlayer().setCursor(Cursor.getDefaultCursor()); // FIXME Poner algo que indique que esas casillas paila
            }
        } else {
            view.getCanvasLoadPlayer().setCursor(Cursor.getDefaultCursor());
        }
    }

    private boolean allShipsAllocated() {
        return model.getBoard().allShipsAllocated();
    }

    private boolean shipFits(Point point, Ship.Type shipType, Ship.Orientation shipOrientation) {
        return model.getBoard().shipFits(point, shipType, shipOrientation);
    }

    private boolean boardIsEmpty(Point point, Ship.Type shipType, Ship.Orientation shipOrientation) {
        return !model.getBoard().thereIsShip(point, shipType, shipOrientation);
    }

}
