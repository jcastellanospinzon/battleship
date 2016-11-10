package edu.udistrital.battleship.client.start;

import edu.udistrital.battleship.client.BattleshipFrame;
import edu.udistrital.battleship.client.mvc.View;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Created by julian on 11/10/16.
 */
public class ViewStart extends View<ControllerStart> {

    @Override
    public void initRootComponent() {
        JButton btnNewGame = new JButton("Start a New Game");
        btnNewGame.addActionListener(e -> newGameEvent());
        JButton btnJoinGame = new JButton("Join an Existing Game");
        btnJoinGame.addActionListener(e -> joinGameEvent());
        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(btnNewGame, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
        rootPanel.add(btnJoinGame, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    private void newGameEvent() {
        controller.newGame();
    }

    private void joinGameEvent() {
        controller.joinGame();
    }

    public void renderViewNewGame() {
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_NEW_GAME);
    }

    public void renderViewJoinGame() {
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_JOIN_GAME);
    }

}
