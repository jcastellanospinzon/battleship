package edu.udistrital.battleship.client.joingame;

import edu.udistrital.battleship.client.mvc.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class ModelJoinGame
    extends Model<ViewJoinGame> {

    private static final Logger LOGGER = LogManager.getLogger(ModelJoinGame.class);

    public ModelJoinGame() {
        super();
    }

    public void joinGame(String playerName, String hostname, int port) {
        LOGGER.debug("Joining game with player name: {} in host: {} and port: {}", playerName, hostname, port);
        business.startClient(playerName, hostname, port);
        view.renderViewLoadPlayer();
        view.renderMessage("The game is ready to start playing, load your ships!", "Info", INFORMATION_MESSAGE);
    }

}
