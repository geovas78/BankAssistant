/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankprogramguiwithdatabase;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author GOGO
 */
public class ChangeTypeOfAccount extends JPanel implements ActionListener {

    private JRadioButton classicButton, goldenButton;
    private JButton submit;
    private JPanel accountType;
    private JLabel label;
    private JLabel info;
    private JTextField field;

    java.net.URL imageURL = ChangeTypeOfAccount.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog", Font.BOLD, 20);

    public ChangeTypeOfAccount() {
        setLayout(new BorderLayout());
        accountType = new JPanel();
        //set up typePanel
        label = new JLabel("Enter the account number : ");
        field = new JTextField(8);
        classicButton = new JRadioButton("Classic");
        goldenButton = new JRadioButton("Golden");
        submit = new JButton("SUBMIT");
        info = new JLabel("");

        accountType.add(label);
        accountType.add(field);
        accountType.add(classicButton);
        accountType.add(goldenButton);
        accountType.add(submit);
        accountType.add(info);

        classicButton.setSelected(true);

        //put two buttons in a group
        ButtonGroup group = new ButtonGroup();
        group.add(classicButton);
        group.add(goldenButton);
        accountType.setBorder(new TitledBorder(new EtchedBorder(), "Account Type"));

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
        add("Center", accountType);

        submit.addActionListener(this);
    }

    public void clear() {
        field.setText("");
        info.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            info.setText("");
            if (classicButton.isSelected()) {
                String numberEntered = field.getText();

                if (numberEntered.length() == 0) {
                    info.setText("ACCOUNT NUMBER MUST BE ENTERED");
                    //setVisible(true);
                } else if (numberEntered.length() != 8) {
                    info.setText("ACCOUNT NUMBER MUST BE 8 DIGITS");
                    //setVisible(true);
                } else if (Integer.parseInt(numberEntered) < 0) {
                    info.setText("ACCOUNT NUMBER CANNOT BE NEGATIVE");
                    //setVisible(true);
                } else {

                    try {

                        String infoToSend = numberEntered + ",Classic,0.00";

                        String accountCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                        URL url = new URL("https://webbank-gvasilski.rhcloud.com/ChangeTypeAccount");
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
                            info.setText(feedback);
                        }
                        in.close();

                    } catch (Exception exception) {
                        info.setText("ERROR: " + exception.getMessage());
                    }
                }
            }
            if (goldenButton.isSelected()) {
                String numberEntered = field.getText();

                if (numberEntered.length() == 0) {
                    info.setText("ACCOUNT NUMBER MUST BE ENTERED");
                    //setVisible(true);
                } else if (numberEntered.length() > 8) {
                    info.setText("ACCOUNT NUMBER MUST BE 8 DIGITS");
                    //setVisible(true);
                } else if (Integer.parseInt(numberEntered) < 0) {
                    info.setText("ACCOUNT NUMBER CANNOT BE NEGATIVE");
                    //setVisible(true);
                } else {
                    try {

                        String infoToSend = numberEntered + ",Golden,500.00";

                        String accountCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                        URL url = new URL("https://webbank-gvasilski.rhcloud.com/ChangeTypeAccount");
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
                            info.setText(feedback);
                        }
                        in.close();

                    } catch (Exception exception) {
                        info.setText("ERROR: " + exception.getMessage());
                    }
                }
            }
        }
    }
}
