package bankprogramguiwithdatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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
public class DisplayOneAccountPanel extends JPanel {

    //other containers and panels
    private JPanel topN;
    private JPanel topS;
    private JPanel centralN;
    private JPanel real;
    private DisplayAccountOneArea central;
    private int showControl = 0;

    private JLabel infoLabel = new JLabel("Enter account number : ");
    private JTextField field = new JTextField(8);
    private JButton okButton = new JButton("   O K    ");
    private JButton cancelButton = new JButton("CANCEL");
    private JLabel messageLabel = new JLabel();
    //private JTextArea area = new JTextArea(10, 35);

    private Font font = new Font("Dialog", Font.BOLD, 15);

    java.net.URL imageURL = DisplayOneAccountPanel.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog", Font.BOLD, 20);

    public DisplayOneAccountPanel() {
        setLayout(new BorderLayout());
        //elements from the top panel
        topN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topN.add(infoLabel);
        topN.add(field);

        //top south panel with buttons
        //topS = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        //topS.add(okButton);
        //topS.add(cancelButton);
        topS = new JPanel(new BorderLayout());
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        messageLabel.setForeground(Color.red);
        messageLabel.setFont(font);
        messagePanel.add(messageLabel);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        topS.add(messagePanel, BorderLayout.CENTER);
        topS.add(buttonPanel, BorderLayout.EAST);

        centralN = new JPanel(new BorderLayout());
        centralN.add(topN, BorderLayout.NORTH);
        centralN.add(topS, BorderLayout.SOUTH);

        //messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //messagePanel.add(messageLabel);
        //central.add(area);
        //area.setText("");
        //area.setEditable(false);
        //area.setFont(font);
        //area.setForeground(Color.black);
        add(centralN, BorderLayout.NORTH);
        //add(messagePanel, BorderLayout.CENTER);

        real = new JPanel(new BorderLayout());
        real.add(centralN, BorderLayout.NORTH);
        //real.add(messagePanel, BorderLayout.CENTER);

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

        clearFields();

        okButton.addActionListener(new ShowListener());
        cancelButton.addActionListener(new CancelListener());
    }

    public void clearFields() {
        if (showControl == 1) {
            real.remove(central);
            real.revalidate();
            real.repaint();
            showControl = 0;
        }
        field.setText("");
        messageLabel.setText("");
    }

    private class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (showControl == 1) {
                real.remove(central);
                real.revalidate();
                real.repaint();
                showControl = 0;
                field.setText("");
            }
            messageLabel.setText("");
        }

    }

    private class ShowListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (showControl == 1) {
                real.remove(central);
                real.revalidate();
                real.repaint();
                showControl = 0;
            }

            String numberEntered = field.getText();

            if (numberEntered.length() == 0) {
                clearFields();
                messageLabel.setText("No account number entered");
            }
            if (numberEntered.length() > 8 || numberEntered.length() < 8) {
                clearFields();
                messageLabel.setText("Account number must be 8 digits");
            } else {

                try {
                    String accountCodedSent = URLEncoder.encode(numberEntered, "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/DisplayOneAccount");
                    java.net.URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write("string=" + accountCodedSent);
                    out.close();

                    System.out.println(accountCodedSent);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));
                    String decodedString;
                    if ((decodedString = in.readLine()) != null) {

                        String[] value = decodedString.split("[,]");
                        System.out.println(decodedString);
                            messageLabel.setText("");
                            central = new DisplayAccountOneArea(Integer.parseInt(value[0]),
                                    value[1], value[2], value[3], Double.parseDouble(value[4]),
                                    value[5], value[6], value[7], value[8], value[9],
                                    Double.parseDouble(value[10]));

                            add(central, BorderLayout.CENTER);
                            real.add(central, BorderLayout.CENTER);
                            add("Center", real);
                            showControl = 1;
                    }
                    else
                    {
                      if (showControl == 1) {
                                real.remove(central);
                                real.revalidate();
                                real.repaint();
                                showControl = 0;
                            }
                            //show a message for non-existing account
                            messageLabel.setText("THE ACCOUNT NUMBER DOESN'T EXIST");      
                            }
                    in.close();
                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }

    }
}
