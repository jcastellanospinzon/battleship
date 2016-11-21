package edu.udistrital.battleship.client.start;

import edu.udistrital.battleship.client.BattleshipFrame;
import edu.udistrital.battleship.client.mvc.View;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewStart
    extends View<ControllerStart> {

    private JButton btnNewGame;

    private JButton btnJoinGame;

    public ViewStart() {
        super();
    }

    @Override
    public void initRootPanel() {
        btnNewGame = new JButton("Start a New Game");
        btnNewGame.addActionListener(controller);
        btnJoinGame = new JButton("Join an Existing Game");
        btnJoinGame.addActionListener(controller);
        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(btnNewGame, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
        rootPanel.add(btnJoinGame, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    public void renderViewNewGame() {
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_NEW_GAME);
    }

    public void renderViewJoinGame() {
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_JOIN_GAME);
    }

    public JButton getBtnNewGame() {
        return btnNewGame;
    }

    public JButton getBtnJoinGame() {
        return btnJoinGame;
    }

}
