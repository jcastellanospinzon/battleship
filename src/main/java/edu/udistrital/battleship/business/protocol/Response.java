package edu.udistrital.battleship.business.protocol;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Response {

    OKAY("OK"),

    NOT_OKAY("NK");

    private final String response;

    Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public static Optional<Response> fromResponse(String response) {
        return Arrays.stream(Response.values())
                   .filter(r -> Objects.equals(r.response, response))
                   .findFirst();
    }

}
