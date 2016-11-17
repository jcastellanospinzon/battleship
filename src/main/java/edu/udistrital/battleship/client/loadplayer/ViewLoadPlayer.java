package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.business.game.Ship.Type;
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

    private JLabel lblInstructions;

    private JLabel lblShips;

    private JComboBox<String> cmbShips;

    private ButtonGroup btnGrpOrientation;

    private JToggleButton btnVerticalShip;

    private JToggleButton btnHorizontalShip;

    private List<String> availableShips;

    private Map<String, Ship.Type> shipTypes;

    private List<Ship> ships;

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

        ships = new ArrayList<>();
    }

    @Override
    public void initRootPanel() {
        canvasLoadPlayer = new JCanvasBattleshipBoard();
        canvasLoadPlayer.addMouseListener(controller);
        canvasLoadPlayer.addMouseMotionListener(controller);
        btnPlay = new JButton("Play!");
        btnPlay.addActionListener(controller);
        btnPlay.setEnabled(false);

        lblInstructions = new JLabel("Select the ship, the orientation and set it in the board!");
        lblShips = new JLabel("Available ships");
        cmbShips = new JComboBox<>(new Vector<>(availableShips));

        btnVerticalShip = new JToggleButton("Vertical");
        btnHorizontalShip = new JToggleButton("Horizontal");
        btnGrpOrientation = new ButtonGroup();
        btnGrpOrientation.add(btnHorizontalShip);
        btnGrpOrientation.add(btnVerticalShip);
        btnVerticalShip.setSelected(true);

        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(canvasLoadPlayer, new GridBagConstraints(0, 0, 1, 3, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(btnPlay, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 20), 0, 0));
        rootPanel.add(lblInstructions, new GridBagConstraints(1, 0, 3, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(lblShips, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(cmbShips, new GridBagConstraints(2, 1, 2, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(btnVerticalShip, new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        rootPanel.add(btnHorizontalShip, new GridBagConstraints(3, 2, 1, 1, 0.0D, 0.0D, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
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

    public List<Ship> getShips() {
        return ships;
    }

    public void addShip(Ship ship) {
        ships.add(ship);
        cmbShips.removeItemAt(cmbShips.getSelectedIndex());
        btnVerticalShip.setSelected(true);
        validatePlay();
    }

    private void validatePlay() {
        if(ships.size() == 10) {
            btnPlay.setEnabled(true);
            cmbShips.setEnabled(false);
            btnVerticalShip.setEnabled(false);
            btnHorizontalShip.setEnabled(false);
        }
    }

}
