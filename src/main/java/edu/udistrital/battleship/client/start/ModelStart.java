package edu.udistrital.battleship.client.start;

import edu.udistrital.battleship.client.mvc.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelStart extends Model<ViewStart> {

    private static final Logger LOGGER = LogManager.getLogger(ModelStart.class);

    public void newGame() {
        view.renderViewNewGame();
    }

    public void joinGame() {
        view.renderViewJoinGame();
    }

}
