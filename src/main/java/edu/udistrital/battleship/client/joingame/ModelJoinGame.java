package edu.udistrital.battleship.client.joingame;

import edu.udistrital.battleship.client.mvc.Model;

public class ModelJoinGame extends Model<ViewJoinGame> {

    public void joinGame(String hostname, int port) {
        business.startClient(hostname, port);
    }

}
