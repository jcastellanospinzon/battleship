package edu.udistrital.battleship.business.protocol;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum AttackResponse {

    NO_IMPACT("0"),

    IMPACT("1"),

    WIN("2");

    private final String attackResponse;

    AttackResponse(String attackResponse) {
        this.attackResponse = attackResponse;
    }

    public String getAttackResponse() {
        return attackResponse;
    }

    public static Optional<AttackResponse> fromAttackResponse(String attackResponse) {
        return Arrays.stream(AttackResponse.values())
                   .filter(ar -> Objects.equals(ar.attackResponse, attackResponse))
                   .findFirst();
    }

}
