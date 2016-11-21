package edu.udistrital.battleship.business.network;

import edu.udistrital.battleship.business.protocol.Command;
import edu.udistrital.battleship.business.protocol.InvalidMessageException;
import edu.udistrital.battleship.business.protocol.Message;
import edu.udistrital.battleship.business.protocol.Protocol;
import edu.udistrital.battleship.business.protocol.Response;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.nonNull;

public abstract class AbstractClient
    implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(AbstractClient.class);

    protected boolean running;

    protected Message lastMessage;

    protected DataOutputStream dataOutputStream;

    public AbstractClient() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    protected void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    protected void startThreadClient() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    protected void translateMessage(String strReceivedMessage) {
        try {
            Command lastMessageCommand = nonNull(lastMessage) ? lastMessage.getCommand() : null;
            Message message = Protocol.getMessageFromString(strReceivedMessage, lastMessageCommand);
            processMessage(message);
        } catch (InvalidMessageException e) {
            LOGGER.error("Message {} cannot be translated, sending error response", strReceivedMessage, e);
            sendMessage(Protocol.buildMessage().withResponse(Response.NOT_OKAY));
        }
    }

    protected abstract void processMessage(Message message);

    public void sendMessage(Message message) {
        try {
            lastMessage = message;
            String strConnectionMessage = message.getString();

            LOGGER.debug("Writing Message {}", strConnectionMessage);
            dataOutputStream.writeUTF(strConnectionMessage);
            dataOutputStream.flush();
        } catch (IOException e) {
            LOGGER.error("Oops! There is an unexpected error", e);
        }
    }

}
