package edu.udistrital.battleship.business.protocol;

public enum Command {

    CONNECT("CON"),

    READY("LIS"),

    ATTACK("ATK");

    private final String command;

    Command(String command) {
        this.command = command;
    }

}
