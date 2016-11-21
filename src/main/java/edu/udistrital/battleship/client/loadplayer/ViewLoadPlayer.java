package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.business.game.Ship.Type;
import edu.udistrital.battleship.client.BattleshipFrame;
import edu.udistrital.battleship.client.mvc.View;
import edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ViewLoadPlayer extends View<ControllerLoadPlayer> {

    private JCanvasBattleshipBoard canvasLoadPlayer;

    private JButton btnPlay;

    private JComboBox<String> cmbShips;

    private JToggleButton btnVerticalShip;

    private JToggleButton btnHorizontalShip;

    private List<String> availableShips;

    private Map<String, Ship.Type> shipTypes;

    private Board board;

    public ViewLoadPlayer() {
        availableShips = new ArrayList<>();
        availableShips.add("Carrier 1");
        availableShips.add("Battleship 1");
        availableShips.add("Battleship 2");
        availableShips.add("Cruiser 1");
        availableShips.add("Cruiser 2");
        availableShips.add("Cruiser 3");
        availableShips.add("Submarine 1");
        availableShips.add("Submarine 2");
        availableShips.add("Submarine 3");
        availableShips.add("Submarine 4");

        shipTypes = new TreeMap<>();
        shipTypes.put("Carrier 1", Ship.Type.CARRIER);
        shipTypes.put("Battleship 1", Ship.Type.BATTLESHIP);
        shipTypes.put("Battleship 2", Ship.Type.BATTLESHIP);
        shipTypes.put("Cruiser 1", Ship.Type.CRUISER);
        shipTypes.put("Cruiser 2", Ship.Type.CRUISER);
        shipTypes.put("Cruiser 3", Ship.Type.CRUISER);
        shipTypes.put("Submarine 1", Ship.Type.SUBMARINE);
        shipTypes.put("Submarine 2", Ship.Type.SUBMARINE);
        shipTypes.put("Submarine 3", Ship.Type.SUBMARINE);
        shipTypes.put("Submarine 4", Ship.Type.SUBMARINE);
    }

    @Override
    public void initRootPanel() {
        canvasLoadPlayer = new JCanvasBattleshipBoard();
        canvasLoadPlayer.addMouseListener(controller);
        canvasLoadPlayer.addMouseMotionListener(controller);
        btnPlay = new JButton("Play!");
        btnPlay.addActionListener(controller);
        btnPlay.setEnabled(false);

        JLabel lblInstructions = new JLabel("Select the ship, the orientation and set it in the board!");
        JLabel lblShips = new JLabel("Available ships");
        cmbShips = new JComboBox<>(new Vector<>(availableShips));

        btnVerticalShip = new JToggleButton("Vertical");
        btnHorizontalShip = new JToggleButton("Horizontal");
        ButtonGroup btnGrpOrientation = new ButtonGroup();
        btnGrpOrientation.add(btnHorizontalShip);
        btnGrpOrientation.add(btnVerticalShip);
        btnVerticalShip.setSelected(true);

        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(lblInstructions, new GridBagConstraints(0, 0, 4, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(lblShips, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 20, 20), 0, 0));
        rootPanel.add(cmbShips, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 20, 5), 0, 0));
        rootPanel.add(btnVerticalShip, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 20, 5), 0, 0));
        rootPanel.add(btnHorizontalShip, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 5), 0, 0));
        rootPanel.add(canvasLoadPlayer, new GridBagConstraints(0, 2, 4, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
        rootPanel.add(btnPlay, new GridBagConstraints(0, 3, 4, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    public JButton getBtnPlay() {
        return btnPlay;
    }

    public JComboBox<String> getCmbShips() {
        return cmbShips;
    }

    public JToggleButton getBtnHorizontalShip() {
        return btnHorizontalShip;
    }

    public JToggleButton getBtnVerticalShip() {
        return btnVerticalShip;
    }

    public JCanvasBattleshipBoard getCanvasLoadPlayer() {
        return canvasLoadPlayer;
    }

    public Map<String, Type> getShipTypes() {
        return shipTypes;
    }

    public void drawBoard(Board board) {
        this.board = board;
        canvasLoadPlayer.setBoard(board);
        cmbShips.removeItemAt(cmbShips.getSelectedIndex());
        btnVerticalShip.setSelected(true);
        validatePlay();
    }

    private void validatePlay() {
        if (board.getShips().size() == 10) {
            btnPlay.setEnabled(true);
            cmbShips.setEnabled(false);
            btnVerticalShip.setEnabled(false);
            btnHorizontalShip.setEnabled(false);
        }
    }

    public void renderViewPlayGame() {
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_PLAY_GAME);
    }

}
