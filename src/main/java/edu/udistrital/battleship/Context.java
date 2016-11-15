package edu.udistrital.battleship;

import edu.udistrital.battleship.business.Business;
import edu.udistrital.battleship.client.BattleshipFrame;
import edu.udistrital.battleship.client.joingame.ControllerJoinGame;
import edu.udistrital.battleship.client.joingame.ModelJoinGame;
import edu.udistrital.battleship.client.joingame.ViewJoinGame;
import edu.udistrital.battleship.client.loadplayer.ControllerLoadPlayer;
import edu.udistrital.battleship.client.loadplayer.ModelLoadPlayer;
import edu.udistrital.battleship.client.loadplayer.ViewLoadPlayer;
import edu.udistrital.battleship.client.mvc.MVC;
import edu.udistrital.battleship.client.newgame.ControllerNewGame;
import edu.udistrital.battleship.client.newgame.ModelNewGame;
import edu.udistrital.battleship.client.newgame.ViewNewGame;
import edu.udistrital.battleship.client.start.ControllerStart;
import edu.udistrital.battleship.client.start.ModelStart;
import edu.udistrital.battleship.client.start.ViewStart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public enum Context {

    INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(Context.class);

    public static Context getCurrent() {
        return INSTANCE;
    }

    public void init() {
        LOGGER.info("Starting Context...");

        LOGGER.debug("Loading main frame");
        BattleshipFrame battleshipFrame = new BattleshipFrame();
        battleshipFrame.init();

        LOGGER.debug("Loading business facade");
        Business business = new Business();
        business.init();

        LOGGER.debug("Creating and loading MVC triads");
        LOGGER.debug("MVC triad start");
        ModelStart modelStart = new ModelStart();
        ViewStart viewStart = new ViewStart();
        ControllerStart controllerStart = new ControllerStart();
        new MVC<>(modelStart, viewStart, controllerStart, battleshipFrame, BattleshipFrame.VIEW_ID_START, business);
        LOGGER.debug("MVC triad new_game");
        ModelNewGame modelNewGame = new ModelNewGame();
        ViewNewGame viewNewGame = new ViewNewGame();
        ControllerNewGame controllerNewGame = new ControllerNewGame();
        new MVC<>(modelNewGame, viewNewGame, controllerNewGame, battleshipFrame, BattleshipFrame.VIEW_ID_NEW_GAME, business);
        LOGGER.debug("MVC triad join_game");
        ModelJoinGame modelJoinGame = new ModelJoinGame();
        ViewJoinGame viewJoinGame = new ViewJoinGame();
        ControllerJoinGame controllerJoinGame = new ControllerJoinGame();
        new MVC<>(modelJoinGame, viewJoinGame, controllerJoinGame, battleshipFrame, BattleshipFrame.VIEW_ID_JOIN_GAME, business);
        LOGGER.debug("MVC triad load_player");
        ModelLoadPlayer modelLoadPlayer = new ModelLoadPlayer();
        ViewLoadPlayer viewLoadPlayer = new ViewLoadPlayer();
        ControllerLoadPlayer controllerLoadPlayer = new ControllerLoadPlayer();
        new MVC<>(modelLoadPlayer, viewLoadPlayer, controllerLoadPlayer, battleshipFrame, BattleshipFrame.VIEW_ID_LOAD_PLAYER, business);

        LOGGER.debug("Loading default triad into main frame");
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_START);

        LOGGER.info("Context started!");
    }

}
