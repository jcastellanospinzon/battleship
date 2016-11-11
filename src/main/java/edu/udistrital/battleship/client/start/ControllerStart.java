package edu.udistrital.battleship.client.start;

import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by julian on 11/10/16.
 */
public class ControllerStart extends Controller<ModelStart, ViewStart> implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnNewGame())) {
            newGame();
        } else if (e.getSource().equals(view.getBtnJoinGame())) {
            joinGame();
        }
    }

    public void newGame() {
        model.newGame();
    }

    public void joinGame() {
        model.joinGame();
    }
}
