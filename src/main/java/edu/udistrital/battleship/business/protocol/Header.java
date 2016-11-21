package edu.udistrital.battleship.business.protocol;

public enum Header {

    BATTLESHIP("BNAVAL");

    private final String header;

    Header(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

}
