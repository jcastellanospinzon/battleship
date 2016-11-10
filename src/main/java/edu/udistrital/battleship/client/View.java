package edu.udistrital.battleship.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Launcher for the game.
 *
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public class View {

    private static final Logger LOGGER = LogManager.getLogger(View.class);

    private JFrame frmMain;
    private Container container;

    private JPanel pnlStart;
    private JButton btnNewGame;
    private JButton btnJoinGame;

    private JDialog dlgNewGame;

    private JDialog dlgJoinGame;

    public View() {

    }

    public void init() {
        initMainFrame();
        initStartPanel();

        loadStartPanel();
    }

    private void initMainFrame() {
        frmMain = new JFrame("Battleship - The Game");
        frmMain.setIconImages(Arrays.asList(new ImageIcon(View.class.getResource("/images/battleship-icon-32.png")).getImage(),
                                            new ImageIcon(View.class.getResource("/images/battleship-icon-64.png")).getImage(),
                                            new ImageIcon(View.class.getResource("/images/battleship-icon-128.png")).getImage()));
        frmMain.setSize(1024, 768);
        frmMain.setResizable(false);
        frmMain.setLocationRelativeTo(null);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.setVisible(true);

        container = frmMain.getContentPane();
        container.setLayout(new BorderLayout());
    }

    private void initStartPanel() {
        btnNewGame = new JButton("Start a New Game");
        btnJoinGame = new JButton("Join an Existing Game");
        pnlStart = new JPanel(new GridBagLayout());
        pnlStart.add(btnNewGame, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
        pnlStart.add(btnJoinGame, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    private void loadStartPanel() {
        container.add(pnlStart, BorderLayout.CENTER);
        frmMain.revalidate();
    }

}
