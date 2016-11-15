package edu.udistrital.battleship.client.newgame;

import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.nonNull;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ControllerNewGame extends Controller<ModelNewGame, ViewNewGame> implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnNew())) {
            newGame();
        }
    }

    private void newGame() {
        Object portValue = view.getTxtPort().getValue();
        if (nonNull(portValue)) {
            int port = (Integer) portValue;
            model.newGame(port);
        } else {
            view.renderMessage("Invalid port value, it must be an integer between 1025 and 32768", "Error", ERROR_MESSAGE);
        }
    }

}
