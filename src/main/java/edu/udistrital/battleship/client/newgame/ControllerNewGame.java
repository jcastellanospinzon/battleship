package edu.udistrital.battleship.client.newgame;

import edu.udistrital.battleship.client.mvc.Controller;

/**
 * Created by julian on 11/10/16.
 */
public class ControllerNewGame extends Controller<ModelNewGame> {

    public void newGame(int port) {
        model.newGame(port);
    }

}
