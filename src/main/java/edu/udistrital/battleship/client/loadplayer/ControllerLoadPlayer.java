package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ControllerLoadPlayer extends Controller<ModelLoadPlayer, ViewLoadPlayer> implements ActionListener,MouseListener, MouseMotionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnPlay())) {
            play();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

}
