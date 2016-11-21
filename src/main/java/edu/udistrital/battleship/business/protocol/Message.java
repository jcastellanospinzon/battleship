package edu.udistrital.battleship.business.protocol;

import edu.udistrital.battleship.business.game.Point;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class Message {

    public static final String COMMAND_SEPARATOR = ":";

    public static final String PARAM_SEPARATOR = ",";

    private Header header;

    private Command command;

    private Response response;

    private String name;

    private Point point;

    private AttackResponse attackResponse;

    private Message() {
    }

    static Message buildMessage() {
        Message message = new Message();
        message.header = Header.BATTLESHIP;
        return message;
    }

    public Message withCommand(Command command) {
        this.command = command;
        this.response = null;
        return this;
    }

    public Message withResponse(Response response) {
        this.response = response;
        this.command = null;
        return this;
    }

    public Message withName(String name) {
        this.name = name;
        return this;
    }

    public Message withPoint(Point point) {
        this.point = point;
        return this;
    }

    public Message withAttackResponse(AttackResponse attackResponse) {
        this.attackResponse = attackResponse;
        return this;
    }

    public boolean isCommand() {
        return nonNull(command);
    }

    public boolean isCommand(Command command) {
        return isCommand() && Objects.equals(this.command, command);
    }

    public boolean isResponse() {
        return nonNull(response);
    }

    public boolean isResponse(Response response) {
        return isResponse() && Objects.equals(this.response, response);
    }

    public String getString() {
        StringBuilder messageBuilder = new StringBuilder()
                                           .append(header.getHeader());
        if (isCommand()) {
            messageBuilder
                .append(COMMAND_SEPARATOR)
                .append(command.getCommand());
            if (nonNull(name)) {
                messageBuilder
                    .append(PARAM_SEPARATOR)
                    .append(name);
            }
            if (nonNull(point)) {
                messageBuilder.append(PARAM_SEPARATOR)
                    .append(point.getColumn().getCode())
                    .append(PARAM_SEPARATOR)
                    .append(point.getRow().getCode());
            }
        } else {
            messageBuilder
                .append(COMMAND_SEPARATOR)
                .append(response.getResponse());
            if (nonNull(name)) {
                messageBuilder
                    .append(PARAM_SEPARATOR)
                    .append(name);
            }
            if (nonNull(attackResponse)) {
                messageBuilder
                    .append(PARAM_SEPARATOR)
                    .append(attackResponse.getAttackResponse());

            }
        }
        return messageBuilder.toString();
    }

    public Command getCommand() {
        return command;
    }

    public Response getResponse() {
        return response;
    }

    public String getName() {
        return name;
    }

    public Point getPoint() {
        return point;
    }

    public AttackResponse getAttackResponse() {
        return attackResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return header == message.header &&
                   command == message.command &&
                   response == message.response &&
                   Objects.equals(name, message.name) &&
                   Objects.equals(point, message.point) &&
                   attackResponse == message.attackResponse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, command, response, name, point, attackResponse);
    }

}
