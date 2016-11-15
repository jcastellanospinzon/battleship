package edu.udistrital.battleship.client.swing;

import edu.udistrital.battleship.client.mvc.View;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

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

}
