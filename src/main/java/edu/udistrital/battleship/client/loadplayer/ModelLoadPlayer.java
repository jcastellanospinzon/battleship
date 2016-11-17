package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.client.mvc.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelLoadPlayer extends Model<ViewLoadPlayer> {

    private static final Logger LOGGER = LogManager.getLogger(ModelLoadPlayer.class);

    private Board board;

    public ModelLoadPlayer() {
        board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public void addShip(Ship.Type type, Ship.Orientation orientation, Point point) {
        LOGGER.debug("Allocating ship of type {} and orientation {} in point {}", type, orientation, point);
        Ship ship = new Ship(type, orientation, point);
        board.addShip(ship);
        view.addShip(ship);
    }

}
