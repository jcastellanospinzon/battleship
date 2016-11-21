package edu.udistrital.battleship.business.protocol;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Command {

    CONNECT("CON"),

    READY("LIS"),

    ATTACK("ATK");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Optional<Command> fromCommand(String command) {
        return Arrays.stream(Command.values())
                   .filter(c -> Objects.equals(c.command, command))
                   .findFirst();
    }

}
