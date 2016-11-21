package edu.udistrital.battleship.business.game;

public class ShipDoesNotFitException
    extends Exception {

    public ShipDoesNotFitException() {
        super();
    }

    public ShipDoesNotFitException(String message) {
        super(message);
    }

    public ShipDoesNotFitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShipDoesNotFitException(Throwable cause) {
        super(cause);
    }

    protected ShipDoesNotFitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
