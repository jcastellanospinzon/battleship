package edu.udistrital.battleship.client.newgame;

import edu.udistrital.battleship.client.BattleshipFrame;
import edu.udistrital.battleship.client.mvc.View;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ViewNewGame extends View<ControllerNewGame> {

    public static final Logger LOGGER = LogManager.getLogger(ViewNewGame.class);

    private JButton btnNew;

    private JFormattedTextField txtPort;

    @Override
    public void initRootPanel() {
        JLabel lblPort = new JLabel("Port to run server [1025 - 32768]: ");
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(1025);
        numberFormatter.setMaximum(32768);
        txtPort = new JFormattedTextField(numberFormatter);
        btnNew = new JButton("New!");
        btnNew.addActionListener(controller);
        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(lblPort, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(txtPort, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 20), 0, 0));
        rootPanel.add(btnNew, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    public void renderViewLoadPlayer() {
        battleshipFrame.loadPanel(BattleshipFrame.VIEW_ID_LOAD_PLAYER);
    }

    public JButton getBtnNew() {
        return btnNew;
    }

    public JFormattedTextField getTxtPort() {
        return txtPort;
    }
}
