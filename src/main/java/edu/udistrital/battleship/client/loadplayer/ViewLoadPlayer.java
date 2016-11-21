package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.business.game.Board;
import edu.udistrital.battleship.business.game.Ship;
import edu.udistrital.battleship.business.game.Ship.Orientation;
import edu.udistrital.battleship.business.game.Ship.Type;
import edu.udistrital.battleship.client.BattleshipFrame;
import edu.udistrital.battleship.client.mvc.View;
import edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.commons.lang3.tuple.Pair;

public class ViewLoadPlayer
    extends View<ControllerLoadPlayer> {

    private JCanvasBattleshipBoard canvasLoadPlayer;

    private JButton btnPlay;

    private JComboBox<String> cmbShips;

    private List<String> availableShips;

    private Map<String, Ship.Type> shipTypes;

    private Map<Pair<Type, Orientation>, Image> shipCursors;

    private Map<Pair<Type, Orientation>, Image> shipRCursors;

    private Board board;

    public ViewLoadPlayer() {
        super();

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

        shipCursors = new HashMap<>();
        shipCursors.put(Pair.of(Type.CARRIER, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-carrierh-160.png")).getImage());
        shipCursors.put(Pair.of(Type.CARRIER, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-carrierv-160.png")).getImage());
        shipCursors.put(Pair.of(Type.BATTLESHIP, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-battleshiph-120.png")).getImage());
        shipCursors.put(Pair.of(Type.BATTLESHIP, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-battleshipv-120.png")).getImage());
        shipCursors.put(Pair.of(Type.CRUISER, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-cruiserh-80.png")).getImage());
        shipCursors.put(Pair.of(Type.CRUISER, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-cruiserv-80.png")).getImage());
        shipCursors.put(Pair.of(Type.SUBMARINE, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-submarineh-40.png")).getImage());
        shipCursors.put(Pair.of(Type.SUBMARINE, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-submarinev-40.png")).getImage());

        shipRCursors = new HashMap<>();
        shipRCursors.put(Pair.of(Type.CARRIER, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rcarrierh-160.png")).getImage());
        shipRCursors.put(Pair.of(Type.CARRIER, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rcarrierv-160.png")).getImage());
        shipRCursors.put(Pair.of(Type.BATTLESHIP, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rbattleshiph-120.png")).getImage());
        shipRCursors.put(Pair.of(Type.BATTLESHIP, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rbattleshipv-120.png")).getImage());
        shipRCursors.put(Pair.of(Type.CRUISER, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rcruiserh-80.png")).getImage());
        shipRCursors.put(Pair.of(Type.CRUISER, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rcruiserv-80.png")).getImage());
        shipRCursors.put(Pair.of(Type.SUBMARINE, Orientation.HORIZONTAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rsubmarineh-40.png")).getImage());
        shipRCursors.put(Pair.of(Type.SUBMARINE, Orientation.VERTICAL), new ImageIcon(View.class.getResource("/images/battleship-cursor-rsubmarinev-40.png")).getImage());
    }

    @Override
    public void initRootPanel() {
        canvasLoadPlayer = new JCanvasBattleshipBoard();
        canvasLoadPlayer.addMouseListener(controller);
        canvasLoadPlayer.addMouseMotionListener(controller);
        btnPlay = new JButton("Play!");
        btnPlay.addActionListener(controller);
        btnPlay.setEnabled(false);

        JLabel lblSelection = new JLabel("Select the ship and set it in the board!");
        JLabel lblOrientation = new JLabel("To change ship orientation, press 'Ctrl' key.");
        JLabel lblShips = new JLabel("Available ships");
        cmbShips = new JComboBox<>(new Vector<>(availableShips));

        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(lblSelection, new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(lblOrientation, new GridBagConstraints(0, 1, 2, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 5, 20), 0, 0));
        rootPanel.add(lblShips, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 5), 0, 0));
        rootPanel.add(cmbShips, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 20, 20), 0, 0));
        rootPanel.add(canvasLoadPlayer, new GridBagConstraints(0, 3, 2, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
        rootPanel.add(btnPlay, new GridBagConstraints(0, 4, 2, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    public Map<Pair<Type, Orientation>, Image> getShipCursors() {
        return shipCursors;
    }

    public Map<Pair<Type, Orientation>, Image> getShipRCursors() {
        return shipRCursors;
    }

    public JButton getBtnPlay() {
        return btnPlay;
    }

    public JComboBox<String> getCmbShips() {
        return cmbShips;
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
        validatePlay();
    }

    private void validatePlay() {
        if (board.allShipsAllocated()) {
            btnPlay.setEnabled(true);
            cmbShips.setEnabled(false);
        }
    }

    public void renderViewPlayGame() {
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_PLAY_GAME);
    }

}
