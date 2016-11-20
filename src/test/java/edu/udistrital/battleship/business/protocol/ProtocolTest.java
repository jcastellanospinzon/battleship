package edu.udistrital.battleship.business.protocol;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import org.junit.Assert;
import org.junit.Test;

public class ProtocolTest {

    @Test
    public void validateGoodConnectionMessage()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:CON,Julián";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withCommand(Command.CONNECT).withName("Julián");
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, null);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadConnectionMessageNoParameters()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:CON";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, null);
    }

    @Test
    public void validateGoodReadyMessage()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:LIS";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withCommand(Command.READY);
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, null);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadReadyMessageWithParameters()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:LIS,anything";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, null);
    }

    @Test
    public void validateGoodAttackMessage()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:ATK,1,10";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withCommand(Command.ATTACK).withPoint(new Point(Row.ROW_10, Column.COLUMN_1));
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, null);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageNoParameters()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:ATK";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters1()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:ATK,a,b";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters2()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:ATK,0,0";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters3()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:ATK,11,22";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, null);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackMessageBadParameters4()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:ATK,anything";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, null);
    }

    @Test
    public void validateGoodConnectionResponse()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK,Julián";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withResponse(Response.OKAY).withName("Julián");
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, Command.CONNECT);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadConnectionResponseNoParameters()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, Command.CONNECT);
    }

    @Test
    public void validateGoodReadyResponse()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withResponse(Response.OKAY);
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, Command.READY);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadReadyResponseWithParameters()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK,anything";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, Command.READY);
    }

    @Test
    public void validateGoodAttackResponseNoImpact()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK,0";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.NO_IMPACT);
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, Command.ATTACK);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test
    public void validateGoodAttackResponseImpact()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK,1";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.IMPACT);
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, Command.ATTACK);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test
    public void validateGoodAttackResponseWin()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK,2";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withResponse(Response.OKAY).withAttackResponse(AttackResponse.WIN);
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, Command.ATTACK);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackResponseNoParameters()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, Command.ATTACK);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackResponseBadParameters1()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK,3";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, Command.ATTACK);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadAttackResponseBadParameters2()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:OK,anything";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, Command.ATTACK);
    }

    @Test
    public void validateGoodFailResponse()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:NK";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Message expectedMessage = Message.buildMessage().withResponse(Response.NOT_OKAY);
        Message message = Protocol.getMessageFromBytes(bytesConnectionMessage, Command.READY);
        Assert.assertEquals(message, expectedMessage);
    }

    @Test(expected = InvalidMessageException.class)
    public void validateBadFailResponseWithParameters()
        throws InvalidMessageException {
        String connectionMessage = "BNAVAL:NK,anything";
        byte[] bytesConnectionMessage = connectionMessage.getBytes();
        Protocol.getMessageFromBytes(bytesConnectionMessage, Command.READY);
    }

}
