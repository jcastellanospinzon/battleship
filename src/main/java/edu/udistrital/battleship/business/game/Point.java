package edu.udistrital.battleship.business.game;

public class Point {

    private final Row row;

    private final Column column;

    public Point(Row row, Column column) {
        this.row = row;
        this.column = column;
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }

    enum Row {

        ROW_1("1", 0),
        ROW_2("2", 1),
        ROW_3("3", 2),
        ROW_4("4", 3),
        ROW_5("5", 4),
        ROW_6("6", 5),
        ROW_7("7", 6),
        ROW_8("8", 7),
        ROW_9("9", 8),
        ROW_10("10", 9);

        private final String code;

        private final int arrayPosition;

        Row(String code, int arrayPosition) {
            this.code = code;
            this.arrayPosition = arrayPosition;
        }

        public String getCode() {
            return code;
        }

        public int getArrayPosition() {
            return arrayPosition;
        }

    }

    enum Column {

        COLUMN_A("A", 0),
        COLUMN_B("B", 1),
        COLUMN_C("C", 2),
        COLUMN_D("D", 3),
        COLUMN_E("E", 4),
        COLUMN_F("F", 5),
        COLUMN_G("G", 6),
        COLUMN_H("H", 7),
        COLUMN_I("I", 8),
        COLUMN_J("K", 9);

        private final String code;

        private final int arrayPosition;

        Column(String code, int arrayPosition) {
            this.code = code;
            this.arrayPosition = arrayPosition;
        }

        public String getCode() {
            return code;
        }

        public int getArrayPosition() {
            return arrayPosition;
        }
    }

}
