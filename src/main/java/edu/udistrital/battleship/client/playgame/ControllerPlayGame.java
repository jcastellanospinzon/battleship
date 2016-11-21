package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.client.mvc.Controller;
import edu.udistrital.battleship.client.mvc.View;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Optional;
import javax.swing.ImageIcon;

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
        if (e.getSource().equals(view.getCanvasRivalBoard())) {
            mouseMovement(e.getX(), e.getY());
        }
    }

    private void click(int x, int y) {
        Optional<Point> optPoint = initPoint(x, y);
        if (optPoint.isPresent() && isYourTurn() && !isGameFinished() && enemyBoardIsEmpty(optPoint.get())) {
            model.shot(optPoint.get());
        }
    }

    private void mouseMovement(int x, int y) {
        Optional<Point> optPoint = initPoint(x, y);
        if (isYourTurn()) {
            if(optPoint.isPresent() &&  !isGameFinished()) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                if( enemyBoardIsEmpty(optPoint.get())) {
                    Image image = new ImageIcon(View.class.getResource("/images/battleship-cursor-shot-40.png")).getImage();
                    Cursor cursor = toolkit.createCustomCursor(image, new java.awt.Point(0, 0), "play-game-cursor");
                    view.getCanvasRivalBoard().setCursor(cursor);
                } else {
                    Image image = new ImageIcon(View.class.getResource("/images/battleship-cursor-rshot-40.png")).getImage();
                    Cursor cursor = toolkit.createCustomCursor(image, new java.awt.Point(0, 0), "play-game-cursor");
                    view.getCanvasRivalBoard().setCursor(cursor);
                }
            } else {
                view.getCanvasRivalBoard().setCursor(Cursor.getDefaultCursor());
            }
        } else {
            view.getCanvasRivalBoard().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
    }

    private boolean isYourTurn() {
        return model.isYourTurn();
    }

    private boolean isGameFinished() {
        return model.isGameFinished();
    }

    private boolean enemyBoardIsEmpty(Point point) {
        return !model.getRivalBoard().thereIsShot(point);
    }

}
