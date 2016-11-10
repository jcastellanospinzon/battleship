package edu.udistrital.battleship.client.joingame;

import edu.udistrital.battleship.client.mvc.View;
import edu.udistrital.battleship.client.newgame.ViewNewGame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.util.Objects.nonNull;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Created by julian on 11/10/16.
 */
public class ViewJoinGame extends View<ControllerJoinGame> {
    public static final Logger LOGGER = LogManager.getLogger(ViewNewGame.class);

    private JTextField txtHostname;

    private JFormattedTextField txtPort;

    @Override
    public void initRootComponent() {
        JLabel lblHostname = new JLabel("IP Address or Hostname of the server:");
        txtHostname = new JTextField();
        JLabel lblPort = new JLabel("Port of the server [1025 - 32768]: ");
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(1025);
        numberFormatter.setMaximum(32768);
        txtPort = new JFormattedTextField(numberFormatter);
        JButton btnJoin = new JButton("Join!");
        btnJoin.addActionListener(e -> joinGameEvent());
        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(lblHostname, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(txtHostname, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 10, 20), 0, 0));
        rootPanel.add(lblPort, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 20, 5, 20), 0, 0));
        rootPanel.add(txtPort, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 20), 0, 0));
        rootPanel.add(btnJoin, new GridBagConstraints(0, 4, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    private void joinGameEvent() {
        String hostname = txtHostname.getText();
        Object portValue = txtPort.getValue();
        if (nonNull(hostname) && !hostname.isEmpty() && nonNull(portValue)) {
            int port = (Integer) portValue;
            controller.joinGame(hostname, port);
        } else {
            renderMessage("Invalid hostname or port value, hostname must be not empty, port must be an integer between 1025 and 32768", "Error", ERROR_MESSAGE);
        }
    }

    public void renderViewLoadPlayer() {

    }
}