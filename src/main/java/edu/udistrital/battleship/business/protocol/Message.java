package edu.udistrital.battleship.business.protocol;

import java.io.Serializable;

public class Message<S extends Serializable> implements Serializable {

    private final Type type;

    private final S payload;

    public Message(Type type, S payload) {
        this.type = type;
        this.payload = payload;
    }

    public Type getType() {
        return type;
    }

    public S getPayload() {
        return payload;
    }

    enum Type {



    }

}
