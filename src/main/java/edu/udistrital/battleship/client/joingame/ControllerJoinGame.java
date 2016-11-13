package edu.udistrital.battleship.client.joingame;

import edu.udistrital.battleship.client.mvc.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Objects.nonNull;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class ControllerJoinGame extends Controller<ModelJoinGame, ViewJoinGame> implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnJoin())) {
            joinGame();
        }
    }

    public void joinGame() {
        String hostname = view.getTxtHostname().getText();
        Object portValue = view.getTxtPort().getValue();
        if (nonNull(hostname) && !hostname.isEmpty() && nonNull(portValue)) {
            int port = (Integer) portValue;
            model.joinGame(hostname, port);
        } else {
            view.renderMessage("Invalid hostname or port value, hostname must be not empty, port must be an integer between 1025 and 32768", "Error", ERROR_MESSAGE);
        }
    }

}
