package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.client.mvc.View;
import edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;

public class ViewPlayGame extends View<ControllerPlayGame> {

    private JCanvasBattleshipBoard canvasPlayerBoard;

    private JCanvasBattleshipBoard canvasRivalBoard;

    public ViewPlayGame() {
    }

    @Override
    public void initRootPanel() {
        canvasPlayerBoard = new JCanvasBattleshipBoard();

        canvasRivalBoard = new JCanvasBattleshipBoard();
        canvasRivalBoard.addMouseListener(controller);
        canvasRivalBoard.addMouseMotionListener(controller);

        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(canvasPlayerBoard, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(canvasRivalBoard, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 20), 0, 0));
    }

}
