package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.client.mvc.View;
import edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewPlayGame extends View<ControllerPlayGame> {

    private JLabel lblPlayerBoard;

    private JLabel lblRivalBoard;

    private JCanvasBattleshipBoard canvasPlayerBoard;

    private JCanvasBattleshipBoard canvasRivalBoard;

    public ViewPlayGame() {
    }

    @Override
    public void initRootPanel() {
        lblPlayerBoard = new JLabel("Your Board");
        canvasPlayerBoard = new JCanvasBattleshipBoard();

        lblRivalBoard = new JLabel("Your Rival Board");
        canvasRivalBoard = new JCanvasBattleshipBoard();
        canvasRivalBoard.addMouseListener(controller);
        canvasRivalBoard.addMouseMotionListener(controller);

        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(lblPlayerBoard, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 10, 5, 20), 0, 0));
        rootPanel.add(lblRivalBoard, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 20, 5, 10), 0, 0));
        rootPanel.add(canvasPlayerBoard, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 10, 20, 20), 0, 0));
        rootPanel.add(canvasRivalBoard, new GridBagConstraints(1, 1, 1, 1, 1.0D, 1.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 10), 0, 0));
    }

    public void drawPlayerBoard(Board board) {
        canvasPlayerBoard.setBoard(board);
    }

    public void drawRivalBoard(Board board) {
        canvasRivalBoard.setBoard(board);
    }

}
