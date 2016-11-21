package edu.udistrital.battleship.client.joingame;

import edu.udistrital.battleship.client.mvc.Model;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class ModelJoinGame extends Model<ViewJoinGame> {

    public void joinGame(String playerName, String hostname, int port) {
        business.startClient(playerName, hostname, port);
        view.renderViewLoadPlayer();
        view.renderMessage("The game is ready to start playing, load your ships!", "Info", INFORMATION_MESSAGE);
    }

}
