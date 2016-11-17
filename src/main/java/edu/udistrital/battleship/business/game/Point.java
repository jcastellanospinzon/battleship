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

    @Override
    public String toString() {
        return Point.class.getCanonicalName() + "[row=" + row + ", column=" + column + "]";
    }

    public enum Row {

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

    public enum Column {

        COLUMN_1("1", 0),
        COLUMN_2("2", 1),
        COLUMN_3("3", 2),
        COLUMN_4("4", 3),
        COLUMN_5("5", 4),
        COLUMN_6("6", 5),
        COLUMN_7("7", 6),
        COLUMN_8("8", 7),
        COLUMN_9("9", 8),
        COLUMN_10("10", 9);

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
