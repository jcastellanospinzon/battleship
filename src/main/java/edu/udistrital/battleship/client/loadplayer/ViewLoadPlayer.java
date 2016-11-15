package edu.udistrital.battleship.client.loadplayer;

import edu.udistrital.battleship.client.mvc.View;
import edu.udistrital.battleship.client.swing.JCanvasBattleshipBoard;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewLoadPlayer extends View<ControllerLoadPlayer> {

    private JCanvasBattleshipBoard jCanvasLoadPlayer;

    private JButton btnPlay;

    public ViewLoadPlayer() {
    }

    @Override
    public void initRootPanel() {
        jCanvasLoadPlayer = new JCanvasBattleshipBoard();
        jCanvasLoadPlayer.addMouseListener(controller);
        jCanvasLoadPlayer.addMouseMotionListener(controller);
        btnPlay = new JButton("Play!");
        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(jCanvasLoadPlayer, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(btnPlay, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 20), 0, 0));
    }

    public JButton getBtnPlay() {
        return btnPlay;
    }

}
