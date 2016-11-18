package edu.udistrital.battleship.client.swing;

import edu.udistrital.battleship.business.game.Point;
import edu.udistrital.battleship.business.game.Point.Column;
import edu.udistrital.battleship.business.game.Point.Row;
import edu.udistrital.battleship.client.mvc.View;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import static java.util.Objects.nonNull;

public class JCanvasBattleshipBoard extends JComponent {

    public JCanvasBattleshipBoard() {
        super();
        setSize(new Dimension(444, 444));
        setMinimumSize(new Dimension(444, 444));
        setMaximumSize(new Dimension(444, 444));
        setPreferredSize(new Dimension(444, 444));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon(View.class.getResource("/images/battleship-table-444.png")).getImage(), 0, 0, this );
    }

    public static Point initPoint(int x, int y) {
        Point.Row row = null;
        Point.Column column = null;
        if (x > 42 && x <= 82) {
            column = Column.COLUMN_1;
        } else if (x > 82 && x <= 122) {
            column = Column.COLUMN_2;
        } else if (x > 122 && x <= 162) {
            column = Column.COLUMN_3;
        } else if (x > 162 && x <= 202) {
            column = Column.COLUMN_4;
        } else if (x > 202 && x <= 242) {
            column = Column.COLUMN_5;
        } else if (x > 242 && x <= 282) {
            column = Column.COLUMN_6;
        } else if (x > 282 && x <= 322) {
            column = Column.COLUMN_7;
        } else if (x > 322 && x <= 362) {
            column = Column.COLUMN_8;
        } else if (x > 362 && x <= 402) {
            column = Column.COLUMN_9;
        } else if (x > 402 && x <= 442) {
            column = Column.COLUMN_10;
        }
        if (y > 42 && y <= 82) {
            row = Row.ROW_1;
        } else if (y > 82 && y <= 122) {
            row = Row.ROW_2;
        } else if (y > 122 && y <= 162) {
            row = Row.ROW_3;
        } else if (y > 162 && y <= 202) {
            row = Row.ROW_4;
        } else if (y > 202 && y <= 242) {
            row = Row.ROW_5;
        } else if (y > 242 && y <= 282) {
            row = Row.ROW_6;
        } else if (y > 282 && y <= 322) {
            row = Row.ROW_7;
        } else if (y > 322 && y <= 362) {
            row = Row.ROW_8;
        } else if (y > 362 && y <= 402) {
            row = Row.ROW_9;
        } else if (y > 402 && y <= 442) {
            row = Row.ROW_10;
        }
        if (nonNull(row) && nonNull(column)) {
            return new Point(row, column);
        } else {
            return null;
        }
    }

}
