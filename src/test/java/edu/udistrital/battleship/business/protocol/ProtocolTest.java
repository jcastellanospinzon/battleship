package edu.udistrital.battleship.business.protocol;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import org.junit.Assert;
import org.junit.Test;

public class ProtocolTest {

    @Test
    public void validateGoodstringMessage()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:CON,Julián";
        Message expectedMessage = Protocol.buildMessage().withCommand(Command.CONNECT).withName("Julián");
        Message message = Protocol.getMessageFromString(stringMessage, null);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadstringMessageNoParameters()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:CON";
        Protocol.getMessageFromString(stringMessage, null);
    }

    @Test
    public void validateGoodReadyMessage()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:LIS";
        Message expectedMessage = Protocol.buildMessage().withCommand(Command.READY);
        Message message = Protocol.getMessageFromString(stringMessage, null);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadReadyMessageWithParameters()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:LIS,anything";
        Protocol.getMessageFromString(stringMessage, null);
    }

    @Test
    public void validateGoodAttackMessage()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:ATK,1,10";
        Message expectedMessage = Protocol.buildMessage().withCommand(Command.ATTACK).withPoint(new Point(Row.ROW_10, Column.COLUMN_1));
        Message message = Protocol.getMessageFromString(stringMessage, null);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageNoParameters()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:ATK";
        Protocol.getMessageFromString(stringMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters1()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:ATK,a,b";
        Protocol.getMessageFromString(stringMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters2()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:ATK,0,0";
        Protocol.getMessageFromString(stringMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters3()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:ATK,11,22";
        Protocol.getMessageFromString(stringMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters4()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:ATK,anything";
        Protocol.getMessageFromString(stringMessage, null);
    }

    @Test
    public void validateGoodConnectionResponse()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK,Julián";
        Message expectedMessage = Protocol.buildMessage().withResponse(Response.OKAY).withName("Julián");
        Message message = Protocol.getMessageFromString(stringMessage, Command.CONNECT);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadConnectionResponseNoParameters()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK";
        Protocol.getMessageFromString(stringMessage, Command.CONNECT);
    }

    @Test
    public void validateGoodReadyResponse()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK";
        Message expectedMessage = Protocol.buildMessage().withResponse(Response.OKAY);
        Message message = Protocol.getMessageFromString(stringMessage, Command.READY);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadReadyResponseWithParameters()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK,anything";
        Protocol.getMessageFromString(stringMessage, Command.READY);
    }

    @Test
    public void validateGoodAttackResponseNoImpact()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK,0";
        Message expectedMessage = Protocol.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.NO_IMPACT);
        Message message = Protocol.getMessageFromString(stringMessage, Command.ATTACK);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test
    public void validateGoodAttackResponseImpact()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK,1";
        Message expectedMessage = Protocol.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.IMPACT);
        Message message = Protocol.getMessageFromString(stringMessage, Command.ATTACK);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test
    public void validateGoodAttackResponseWin()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK,2";
        Message expectedMessage = Protocol.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.WIN);
        Message message = Protocol.getMessageFromString(stringMessage, Command.ATTACK);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackResponseNoParameters()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK";
        Protocol.getMessageFromString(stringMessage, Command.ATTACK);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackResponseBadParameters1()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK,3";
        Protocol.getMessageFromString(stringMessage, Command.ATTACK);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackResponseBadParameters2()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:OK,anything";
        Protocol.getMessageFromString(stringMessage, Command.ATTACK);
    }

    @Test
    public void validateGoodFailResponse()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:NK";
        Message expectedMessage = Protocol.buildMessage().withResponse(Response.NOT_OKAY);
        Message message = Protocol.getMessageFromString(stringMessage, Command.READY);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadFailResponseWithParameters()
        throws InvalidMessageException {
        String stringMessage = "BNAVAL:NK,anything";
        Protocol.getMessageFromString(stringMessage, Command.READY);
    }

}
