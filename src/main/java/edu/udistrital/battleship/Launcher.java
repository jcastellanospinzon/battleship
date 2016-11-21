package edu.udistrital.battleship;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Launcher for the game.
 *
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public class Launcher {

    private static final Logger LOGGER = LogManager.getLogger(Launcher.class);

    /**
     * Launch the game.
     *
     * @param args Console arguments. Do nothing.
     */
    public static void main(String[] args) {
        LOGGER.info("***************************");
        LOGGER.info("** BATTLESHIP - THE GAME **");
        LOGGER.info("***************************");
        LOGGER.info("Starting...");

        Context.getCurrent().init();

        LOGGER.info("Started!");
    }

}
