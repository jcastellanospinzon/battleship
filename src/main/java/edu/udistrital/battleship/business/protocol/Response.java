package edu.udistrital.battleship.business.protocol;

public enum Response {

    OKAY("OK"),

    NOT_OKAY("NK");

    private final String response;

    Response(String response) {
        this.response = response;
    }

}
