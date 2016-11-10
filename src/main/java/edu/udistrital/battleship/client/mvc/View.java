package edu.udistrital.battleship.client.mvc;

import edu.udistrital.battleship.client.BattleshipFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public abstract class View<C extends Controller> {

    protected BattleshipFrame battleshipFrame;

    protected C controller;

    protected JPanel rootPanel;

    public View() {
    }

    public void setBattleshipFrame(BattleshipFrame battleshipFrame) {
        this.battleshipFrame = battleshipFrame;
    }

    public void setController(C controller) {
        this.controller = controller;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void renderMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(battleshipFrame.getFrmMain(), message, title, messageType);
    }

    public abstract void initRootComponent();

}
