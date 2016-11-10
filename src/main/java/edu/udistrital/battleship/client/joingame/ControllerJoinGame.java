package edu.udistrital.battleship.client.joingame;

import edu.udistrital.battleship.client.mvc.Controller;

/**
 * Created by julian on 11/10/16.
 */
public class ControllerJoinGame extends Controller<ModelJoinGame> {

    public void joinGame(String hostname, int port) {
        model.joinGame(hostname, port);
    }

}
