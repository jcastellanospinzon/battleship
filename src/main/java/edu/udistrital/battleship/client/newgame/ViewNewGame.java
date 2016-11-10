package edu.udistrital.battleship.client.newgame;

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

import static java.util.Objects.nonNull;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Created by julian on 11/10/16.
 */
public class ViewNewGame extends View<ControllerNewGame> {

    public static final Logger LOGGER = LogManager.getLogger(ViewNewGame.class);

    private JFormattedTextField txtPort;

    @Override
    public void initRootComponent() {
        JLabel lblPort = new JLabel("Port to run server [1025 - 32768]: ");
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setMinimum(1025);
        numberFormatter.setMaximum(32768);
        txtPort = new JFormattedTextField(numberFormatter);
        JButton btnNew = new JButton("New!");
        btnNew.addActionListener(e -> newGameEvent());
        rootPanel = new JPanel(new GridBagLayout());
        rootPanel.add(lblPort, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 5, 20), 0, 0));
        rootPanel.add(txtPort, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 20, 20, 20), 0, 0));
        rootPanel.add(btnNew, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));
    }

    private void newGameEvent() {
        Object portValue = txtPort.getValue();
        if (nonNull(portValue)) {
            int port = (Integer) portValue;
            controller.newGame(port);
        } else {
            renderMessage("Invalid port value, it must be an integer between 1025 and 32768", "Error", ERROR_MESSAGE);
        }
    }

    public void renderViewLoadPlayer() {

    }

}
