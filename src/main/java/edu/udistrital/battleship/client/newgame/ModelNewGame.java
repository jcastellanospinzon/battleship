package edu.udistrital.battleship.client.newgame;

import edu.udistrital.battleship.client.mvc.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class ModelNewGame extends Model<ViewNewGame> {

    public static final Logger LOGGER = LogManager.getLogger(ModelNewGame.class);

    public void newGame(int port) {
        business.startServer(port);
        business.startClient("localhost", port);
        view.renderViewLoadPlayer();
        view.renderMessage("The game is ready to start playing, load your ships!", "Info", INFORMATION_MESSAGE);
    }

}
