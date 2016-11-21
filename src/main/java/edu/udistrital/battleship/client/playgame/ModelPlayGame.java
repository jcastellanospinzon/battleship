package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.business.BusinessObserver;
import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.client.mvc.Model;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModelPlayGame
    extends Model<ViewPlayGame>
    implements BusinessObserver {

    private static final Logger LOGGER = LogManager.getLogger(ModelPlayGame.class);

    public ModelPlayGame() {
        super();
    }

    @Override
    public void init() {
        super.init();
        view.drawPlayerBoard(business.getPlayerBoard());
        view.drawRivalBoard(business.getRivalBoard());
        view.drawResult("Wait for your turn!", false);
    }

    @Override
    public void notifyChange() {
        view.drawPlayerBoard(business.getPlayerBoard());
        view.drawRivalBoard(business.getRivalBoard());
        if (business.isGameFinished()) {
            if (business.isPlayerWin()) {
                view.drawResult("You WIN!", true);
            } else {
                view.drawResult("You LOSE!", false);
            }
        } else {
            if (business.isPlayerTurn()) {
                view.drawResult("Your Turn!", true);
            } else {
                view.drawResult("Wait for your turn!", false);
            }
        }
    }

    public Board getRivalBoard() {
        return business.getRivalBoard();
    }

    public boolean isGameFinished() {
        return business.isGameFinished();
    }

    public boolean isPlayerTurn() {
        return business.isPlayerTurn();
    }

    public void shot(Point point) {
        LOGGER.debug("Shooting at point: {}", point);
        business.doShot(point);
    }

}
