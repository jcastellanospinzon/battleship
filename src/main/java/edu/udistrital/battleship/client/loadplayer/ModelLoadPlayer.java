package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.client.mvc.Model;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelLoadPlayer extends Model<ViewLoadPlayer> {

    private static final Logger LOGGER = LogManager.getLogger(ModelLoadPlayer.class);

    private List<Ship> ships;

    public ModelLoadPlayer() {
        ships = new ArrayList<>();
    }


}
