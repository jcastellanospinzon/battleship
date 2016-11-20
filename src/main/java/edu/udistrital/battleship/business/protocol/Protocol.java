package edu.udistrital.battleship.business.protocol;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public final class Protocol {

    private Protocol() {
    }

    public static Message buildMessage() {
        return Message.buildMessage();
    }

    public static Message getMessageFromString(String strMessage, Command commandSend)
        throws InvalidMessageException {
        Message message = Message.buildMessage();
        String strMessageCommand = validateHeader(strMessage);
        String strMessageParameters = validateCommandResponse(strMessage, strMessageCommand, message);
        if (isNull(commandSend)) {
            validateReadyNoParameters(strMessage, strMessageParameters, message);
            validateConnectionName(strMessage, strMessageParameters, message);
            validateAttackPoint(strMessage, strMessageParameters, message);
        } else {
            validateOkayNoParameters(strMessage, strMessageParameters, message, commandSend);
            validateOkayName(strMessage, strMessageParameters, message, commandSend);
            validateOkayAttackResponse(strMessage, strMessageParameters, message, commandSend);
            validateNotOkayNoParameters(strMessage, strMessageParameters, message);
        }
        return message;
    }

    private static String validateHeader(String strMessage)
        throws InvalidMessageException {
        String expectedHeader = Header.BATTLESHIP.getHeader() + Message.COMMAND_SEPARATOR;
        if (strMessage.startsWith(expectedHeader)) {
            return strMessage.replace(expectedHeader, "");
        } else {
            throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. Header validation fail.");
        }
    }

    private static String validateCommandResponse(String strMessage, String strMessageCommand, Message message)
        throws InvalidMessageException {
        String strCommandResponse = strMessageCommand;
        String strParameters = null;
        if (strMessageCommand.contains(Message.PARAM_SEPARATOR)) {
            strCommandResponse = strMessageCommand.substring(0, strMessageCommand.indexOf(Message.PARAM_SEPARATOR));
            strParameters = strMessageCommand.substring(strMessageCommand.indexOf(Message.PARAM_SEPARATOR) + 1, strMessageCommand.length());
        }
        Optional<Command> optCommand = Command.byCommand(strCommandResponse);
        Optional<Response> optResponse = Response.byResponse(strCommandResponse);
        if (!optCommand.isPresent() && !optResponse.isPresent()) {
            throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. Command validation fail.");
        } else if (optCommand.isPresent()) {
            message.withCommand(optCommand.get());
        } else {
            message.withResponse(optResponse.get());
        }
        return strParameters;
    }

    private static void validateReadyNoParameters(String strMessage, String strParameters, Message message)
        throws InvalidMessageException {
        if (message.isCommand(Command.READY)) {
            if (nonNull(strParameters)) {
                throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. There is parameters.");
            }
        }
    }

    private static void validateConnectionName(String strMessage, String strParameters, Message message)
        throws InvalidMessageException {
        if (message.isCommand(Command.CONNECT)) {
            if (isNull(strParameters)) {
                throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. Name validation fail.");
            } else {
                message.withName(strParameters);
            }
        }
    }

    private static void validateAttackPoint(String strMessage, String strParameters, Message message)
        throws InvalidMessageException {
        if (message.isCommand(Command.ATTACK)) {
            if (isNull(strParameters) || !Pattern.compile("([1-9]|10),([1-9]|10)").matcher(strParameters).matches()) {
                throw new InvalidMessageException("The current message " + strMessage + " isn't a valida message. Point validation fail.");
            } else {
                String[] splitStrParameters = strParameters.split(Message.PARAM_SEPARATOR);
                Optional<Row> optRow = Row.fromCode(splitStrParameters[1]);
                Optional<Column> optColumn = Column.fromCode(splitStrParameters[0]);
                if (!optRow.isPresent() || !optColumn.isPresent()) {
                    throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. Point validation fail.");
                } else {
                    message.withPoint(new Point(optRow.get(), optColumn.get()));
                }
            }
        }
    }

    private static void validateOkayNoParameters(String strMessage, String strParameters, Message message, Command commandSend)
        throws InvalidMessageException {
        if (Objects.equals(commandSend, Command.READY) && message.isResponse(Response.OKAY)) {
            if (nonNull(strParameters)) {
                throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. There is parameters.");
            }
        }
    }

    private static void validateOkayName(String strMessage, String strParameters, Message message, Command commandSend)
        throws InvalidMessageException {
        if (Objects.equals(commandSend, Command.CONNECT) && message.isResponse(Response.OKAY)) {
            if (isNull(strParameters)) {
                throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. Name validation fail.");
            } else {
                message.withName(strParameters);
            }
        }
    }

    private static void validateOkayAttackResponse(String strMessage, String strParameters, Message message, Command commandSend)
        throws InvalidMessageException {
        if (Objects.equals(commandSend, Command.ATTACK) && message.isResponse(Response.OKAY)) {
            if (isNull(strParameters)) {
                throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. Attack response validation fail.");
            } else {
                Optional<AttackResponse> optAttackResponse = AttackResponse.byAttackResponse(strParameters);
                if (!optAttackResponse.isPresent()) {
                    throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. Attack response validation fail.");
                } else {
                    message.withAttackResponse(optAttackResponse.get());
                }
            }
        }
    }

    private static void validateNotOkayNoParameters(String strMessage, String strParameters, Message message)
        throws InvalidMessageException {
        if (message.isResponse(Response.NOT_OKAY)) {
            if (nonNull(strParameters)) {
                throw new InvalidMessageException("The current message " + strMessage + " isn't a valid message. There is parameters.");
            }
        }
    }

}
