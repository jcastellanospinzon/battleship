package edu.udistrital.battleship.client.playgame;

import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.client.mvc.View;
import edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ViewPlayGame
    extends View<ControllerPlayGame> {

    private JCanvasBattleshipBoard canvasPlayerBoard;

    private JCanvasBattleshipBoard canvasRivalBoard;

    private JLabel lblResult;

    public ViewPlayGame() {
        super();
    }

    @Override
    public void initRootPanel() {
        JLabel lblPlayerBoard = new JLabel("Your Board");
        canvasPlayerBoard = new JCanvasBattleshipBoard();

        JLabel lblRivalBoard = new JLabel("Your Rival Board");
        canvasRivalBoard = new JCanvasBattleshipBoard();
        canvasRivalBoard.addMouseListener(controller);
        canvasRivalBoard.addMouseMotionListener(controller);

        lblResult = new JLabel();

        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(lblPlayerBoard, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 20, 5, 10), 0, 0));
        rootPanel.add(lblRivalBoard, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 10, 5, 20), 0, 0));
        rootPanel.add(canvasPlayerBoard, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 10), 0, 0));
        rootPanel.add(canvasRivalBoard, new GridBagConstraints(1, 1, 1, 1, 1.0D, 1.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 10, 20, 20), 0, 0));
        rootPanel.add(lblResult, new GridBagConstraints(0, 2, 2, 1, 1.0D, 1.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    public JCanvasBattleshipBoard getCanvasRivalBoard() {
        return canvasRivalBoard;
    }

    public void drawPlayerBoard(Board board) {
        canvasPlayerBoard.setBoard(board);
    }

    public void drawRivalBoard(Board board) {
        canvasRivalBoard.setBoard(board);
    }

    public void drawResult(String result, boolean success) {
        Color color = success ? Color.GREEN : Color.RED;
        lblResult.setHorizontalAlignment(JLabel.CENTER);
        lblResult.setFont(lblResult.getFont().deriveFont(80F).deriveFont(Font.BOLD).deriveFont(Font.ITALIC));
        lblResult.setForeground(color);
        lblResult.setText(result);
    }

}
