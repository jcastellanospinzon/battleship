package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.business.BusinessObserver;
import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.client.mvc.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelPlayGame extends Model<ViewPlayGame>
    implements BusinessObserver {

    private static final Logger LOGGER = LogManager.getLogger(ModelPlayGame.class);

    public ModelPlayGame() {
    }

    @Override
    public void init() {
        super.init();
        view.drawPlayerBoard(business.getPlayerBoard());
        view.drawRivalBoard(business.getRivalBoard());
    }

    @Override
    public void notifyChange() {
        view.drawPlayerBoard(business.getPlayerBoard());
        view.drawRivalBoard(business.getRivalBoard());
        if (business.isGameFinished()) {
            if (business.isPlayerWin()) {
                view.drawResult("You WIN!");
            } else {
                view.drawResult("You LOSE!");
            }
        }
    }

    public Board getPlayerBoard() {
        return business.getPlayerBoard();
    }

    public Board getRivalBoard() {
        return business.getRivalBoard();
    }

    public void shot(Point point) {
        LOGGER.debug("Shooting at point: {}", point);
        business.doShot(point);
    }

    public boolean isGameFinished() {
        return business.isGameFinished();
    }

}
