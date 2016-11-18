package edu.udistrital.battleship.business.game;

import java.util.Optional;

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
        
        public static Optional<Row> fromCode(String code) {
            switch (code) {
                case "1":
                    return Optional.of(ROW_1);
                case "2":
                    return Optional.of(ROW_2);
                case "3":
                    return Optional.of(ROW_3);
                case "4":
                    return Optional.of(ROW_4);
                case "5":
                    return Optional.of(ROW_5);
                case "6":
                    return Optional.of(ROW_6);
                case "7":
                    return Optional.of(ROW_7);
                case "8":
                    return Optional.of(ROW_8);
                case "9":
                    return Optional.of(ROW_9);
                case "10":
                    return Optional.of(ROW_10);
                default:
                    return Optional.empty();
            }
        }
        
        public static Optional<Row> fromArrayPosition(int arrayPosition) {
            switch (arrayPosition) {
                case 0:
                    return Optional.of(ROW_1);
                case 1:
                    return Optional.of(ROW_2);
                case 2:
                    return Optional.of(ROW_3);
                case 3:
                    return Optional.of(ROW_4);
                case 4:
                    return Optional.of(ROW_5);
                case 5:
                    return Optional.of(ROW_6);
                case 6:
                    return Optional.of(ROW_7);
                case 7:
                    return Optional.of(ROW_8);
                case 8:
                    return Optional.of(ROW_9);
                case 9:
                    return Optional.of(ROW_10);
                default:
                    return Optional.empty();
            }
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

        public static Optional<Column> fromCode(String code) {
            switch (code) {
                case "1":
                    return Optional.of(COLUMN_1);
                case "2":
                    return Optional.of(COLUMN_2);
                case "3":
                    return Optional.of(COLUMN_3);
                case "4":
                    return Optional.of(COLUMN_4);
                case "5":
                    return Optional.of(COLUMN_5);
                case "6":
                    return Optional.of(COLUMN_6);
                case "7":
                    return Optional.of(COLUMN_7);
                case "8":
                    return Optional.of(COLUMN_8);
                case "9":
                    return Optional.of(COLUMN_9);
                case "10":
                    return Optional.of(COLUMN_10);
                default:
                    return Optional.empty();
            }
        }

        public static Optional<Column> fromArrayPosition(int arrayPosition) {
            switch (arrayPosition) {
                case 0:
                    return Optional.of(COLUMN_1);
                case 1:
                    return Optional.of(COLUMN_2);
                case 2:
                    return Optional.of(COLUMN_3);
                case 3:
                    return Optional.of(COLUMN_4);
                case 4:
                    return Optional.of(COLUMN_5);
                case 5:
                    return Optional.of(COLUMN_6);
                case 6:
                    return Optional.of(COLUMN_7);
                case 7:
                    return Optional.of(COLUMN_8);
                case 8:
                    return Optional.of(COLUMN_9);
                case 9:
                    return Optional.of(COLUMN_10);
                default:
                    return Optional.empty();
            }
        }
        
    }

}
