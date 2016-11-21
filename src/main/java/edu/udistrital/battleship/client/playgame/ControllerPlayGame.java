package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;

import static edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard.initPoint;

public class ControllerPlayGame extends Controller<ModelPlayGame, ViewPlayGame>
    implements MouseListener, MouseMotionListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(view.getCanvasRivalBoard()) &&
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

    private void click(int x, int y) {
        Optional<Point> optPoint = initPoint(x, y);
        if (optPoint.isPresent() && !isGameFinished() && enemyBoardIsEmpty(optPoint.get())) {
            model.shot(optPoint.get());
        }
    }

    private boolean isGameFinished() {
        return model.isGameFinished();
    }

    private boolean enemyBoardIsEmpty(Point point) {
        return !model.getRivalBoard().thereIsShot(point);
    }

}
