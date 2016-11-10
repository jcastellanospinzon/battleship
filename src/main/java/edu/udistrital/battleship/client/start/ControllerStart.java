package edu.udistrital.battleship.client.start;

import edu.udistrital.battleship.client.mvc.Controller;

/**
 * Created by julian on 11/10/16.
 */
public class ControllerStart extends Controller<ModelStart> {

    public void newGame() {
        model.newGame();
    }

    public void joinGame() {
        model.joinGame();
    }

}
