package edu.udistrital.battleship.client;

import edu.udistrital.battleship.client.mvc.View;
import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Julián Yezid Castellanos Pinzón <i>&lt;jcastellanospinzon@gmail.com&gt;</i>
 */
public class BattleshipFrame {

    private static final Logger LOGGER = LogManager.getLogger(BattleshipFrame.class);

    public static final String VIEW_ID_START = "start";
    public static final String VIEW_ID_NEW_GAME = "new_game";
    public static final String VIEW_ID_JOIN_GAME = "join_game";

    private JFrame frmMain;
    private Container container;

    private Map<String, View> views;

    public BattleshipFrame() {
        views = new HashMap<>();
    }

    public void init() {
        initMainFrame();
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

    public void subscribeView(String viewId, View view) {
        views.put(viewId, view);
        view.setBattleshipFrame(this);
        view.initRootComponent();
    }

    public void loadPanel(String viewId) {
        container.removeAll();
        container.add(views.get(viewId).getRootPanel(), BorderLayout.CENTER);
        frmMain.revalidate();
    }

    public JFrame getFrmMain() {
        return frmMain;
    }
}
