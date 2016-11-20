package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.business.BusinessObserver;
import edu.udistrital.battleship.client.mvc.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelPlayGame extends Model<ViewPlayGame>
    implements BusinessObserver {

    private static final Logger LOGGER = LogManager.getLogger(ModelPlayGame.class);

    public ModelPlayGame() {
    }

    @Override
    public void notifyChange() {

    }

}
