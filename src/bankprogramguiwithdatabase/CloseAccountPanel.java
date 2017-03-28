package bankprogramguiwithdatabase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.*;
import javax.swing.border.LineBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gogo
 */
public class CloseAccountPanel extends JPanel {

    private JPanel topPanelNorth;
    private JPanel topPanelSouth;
    private JPanel generalNorth;
    private JPanel generalCentre;

    private JLabel textLabel = new JLabel("To delete an account, you have to input account "
            + "number below");
    private JTextField field = new JTextField(10);
    private JButton button = new JButton("COFIRM DELETE");

    private Font font = new Font("Dialog", Font.BOLD, 20);
    JLabel warnLabel = new JLabel();

    java.net.URL imageURL = CloseAccountPanel.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog", Font.BOLD, 20);

    public CloseAccountPanel() {
        setLayout(new BorderLayout());

        //top panel part of north general
        topPanelNorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanelNorth.add(textLabel);

        topPanelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanelSouth.add(field);
        topPanelSouth.add(button);

        //general panel set up
        generalNorth = new JPanel(new BorderLayout());
        generalNorth.add(topPanelNorth, BorderLayout.NORTH);
        generalNorth.add(topPanelSouth, BorderLayout.SOUTH);

        generalCentre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        generalCentre.add(warnLabel);

        //warnLabel set up
        warnLabel.setText("");
        warnLabel.setFont(font);
        warnLabel.setForeground(Color.red);

        add(generalNorth, BorderLayout.NORTH);
        add(generalCentre, BorderLayout.CENTER);

        JPanel real = new JPanel(new BorderLayout());
        real.add(generalNorth, BorderLayout.NORTH);
        real.add(generalCentre, BorderLayout.CENTER);

        //create a toplayer of the window
        JLabel _label = new JLabel();
        _label.setIcon(icon);
        _label.setText("GEORGE INVESTMENT BANK");
        _label.setFont(font99);

        //set up the top panel
        JPanel _topPanel = new JPanel();
        _topPanel.setBorder(new LineBorder(Color.black));
        _topPanel.add(_label);
        add("North", _topPanel);
        add("Center", real);

        button.addActionListener(new ButtonListener());

    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String numberEntered = field.getText();
            if (numberEntered.length() == 0) {
                warnLabel.setText("no account number entered");
                field.setText("");
            } else if (numberEntered.length() != 8) {
                warnLabel.setText("Account number should be exact 8 digits");
                field.setText("");
            } else {
                try {

                    String infoToSend = numberEntered;

                    String accountCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/DeleteAccount");
                    java.net.URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write("string=" + accountCodedString);
                    out.close();

                    //receive the feedback
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));
                    String feedback;
                    while ((feedback = in.readLine()) != null) {
                        warnLabel.setText(feedback);
                        field.setText("");
                    }
                    in.close();

                } catch (Exception ex) {
                    warnLabel.setText("ERROR: " + ex.getMessage());
                }
            }
        }

    }
}
