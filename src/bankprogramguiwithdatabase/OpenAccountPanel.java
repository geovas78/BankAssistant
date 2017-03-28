package bankprogramguiwithdatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gogo
 */
public class OpenAccountPanel extends JPanel {

    //other containers and panels
    private JPanel mainPanel;
    private JPanel accountDataPanel;
    private JPanel accountType;
    private JPanel buttonPanel;
    private JPanel centralConsole;
    private JPanel topCentral;

    //attributes of AccountDataPanel
    private JLabel accountNumber, accountName;
    private JTextField accountNumberField, accountNameField, sortField1,
            sortField2, sortField3;
    private JComboBox titleBox;
    private String[] titles = {"-", "MR.", "MRS.", "MISS", "company"};
    private JPanel _1;
    private JPanel _2;

    //attributes of accountType
    private JRadioButton classicButton, goldenButton;

    //buttonPanel attributes
    private JButton submit, cancel;

    //centralConsole attributes
    private JPanel addressPanel, displayPanel, labelPanel, fieldPanel;
    private JTextArea area = new JTextArea(10, 33);
    private JLabel number, street, town, postCode, telephone;
    private JTextField numberF, streetF, townF, postCodeF, telephoneF;

    private String title;

    java.net.URL imageURL = OpenAccountPanel.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog", Font.BOLD, 20);

    public OpenAccountPanel() {
        setLayout(new BorderLayout());
        //implementing Panel within mainPanel
        //accountDataPanel
        accountDataPanel = new JPanel();

        accountNumber = new JLabel("Account  Number ");
        accountNumberField = new JTextField(8);
        accountNumberField.setEditable(true);
        //some additional info about the sort code
        JLabel sortCodeLabel = new JLabel("date");
        sortField1 = new JTextField(2);
        JLabel dash1 = new JLabel(" - ");
        JLabel dash2 = new JLabel(" - ");
        sortField2 = new JTextField(2);
        sortField3 = new JTextField(4);

        accountName = new JLabel("Account  Name " + "  ");
        titleBox = new JComboBox(titles);
        accountNameField = new JTextField(20);
        accountNameField.setEditable(true);

        accountDataPanel.setLayout(new GridLayout(2, 1));
        accountDataPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "Account Information"));

        _1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        _1.add(accountNumber, FlowLayout.LEFT);
        _1.add(accountNumberField);
        _1.add(sortCodeLabel);
        _1.add(sortField1);
        _1.add(dash1);
        _1.add(sortField2);
        _1.add(dash2);
        _1.add(sortField3);
        _2.add(accountName);
        _2.add(titleBox);
        _2.add(accountNameField);

        accountDataPanel.add(_1);
        accountDataPanel.add(_2);

        //accountType
        accountType = new JPanel();

        //set up typePanel
        classicButton = new JRadioButton("Classic");
        goldenButton = new JRadioButton("Golden");

        accountType.add(classicButton);
        accountType.add(goldenButton);

        classicButton.setSelected(true);

        //put two buttons in a group
        ButtonGroup group = new ButtonGroup();
        group.add(classicButton);
        group.add(goldenButton);
        accountType.setBorder(new TitledBorder(new EtchedBorder(), "Account Type"));

        //buttonPanel
        buttonPanel = new JPanel();

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        submit = new JButton("SUBMIT");
        cancel = new JButton("CLEAR");

        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        //create mainPanel and add all trhee panel above
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(accountDataPanel, BorderLayout.CENTER);
        mainPanel.add(accountType, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        //centralConsole
        centralConsole = new JPanel();

        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(5, 1));

        number = new JLabel("House Number");
        street = new JLabel("Street Name");
        town = new JLabel("Town/City");
        postCode = new JLabel("Post code");
        telephone = new JLabel("Telephone");

        labelPanel.add(number);
        labelPanel.add(street);
        labelPanel.add(town);
        labelPanel.add(postCode);
        labelPanel.add(telephone);

        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(5, 1));

        numberF = new JTextField(8);
        streetF = new JTextField(8);
        townF = new JTextField(8);
        postCodeF = new JTextField(8);
        telephoneF = new JTextField(8);

        numberF.setEditable(true);
        streetF.setEditable(true);
        townF.setEditable(true);
        postCodeF.setEditable(true);
        telephoneF.setEditable(true);

        fieldPanel.add(numberF);
        fieldPanel.add(streetF);
        fieldPanel.add(townF);
        fieldPanel.add(postCodeF);
        fieldPanel.add(telephoneF);

        addressPanel = new JPanel();
        addressPanel.setLayout(new GridLayout(1, 2));
        addressPanel.add(labelPanel);
        addressPanel.add(fieldPanel);

        displayPanel = new JPanel(new BorderLayout());
        area.setText(" W E L C O M E ");
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        displayPanel.add(scroll, BorderLayout.CENTER);

        centralConsole.setLayout(new BorderLayout());
        displayPanel.setBorder(new TitledBorder(new LineBorder(Color.black), "DISPLAY",
                TitledBorder.CENTER, TitledBorder.ABOVE_TOP));
        addressPanel.setBorder(new TitledBorder(new EtchedBorder(), "Address:"));
        centralConsole.add(addressPanel, BorderLayout.WEST);
        centralConsole.add(displayPanel, BorderLayout.CENTER);

        //topCentralPanel with two panels
        topCentral = new JPanel();
        topCentral.setLayout(new BorderLayout());
        topCentral.add(mainPanel, BorderLayout.NORTH);
        topCentral.add(centralConsole, BorderLayout.CENTER);

        //add(topCentral);
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
        add("Center", topCentral);

        submit.addActionListener(new SubmitListener());
        cancel.addActionListener(new CancelListener());
        titleBox.addActionListener(new BoxListener());

    }

    private class BoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String item = (String) titleBox.getSelectedItem();

            if (item.equals("MR.")) {
                title = "Mr. ";
            }
            if (item.equals("MRS.")) {
                title = "Mrs. ";
            }
            if (item.equals("MISS")) {
                title = "Miss ";
            }
            if (item.equals("company")) {
                title = "";
            }
        }

    }

    private class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //clear all the fields
            accountNumberField.setText("");
            accountNameField.setText("");
            numberF.setText("");
            streetF.setText("");
            townF.setText("");
            postCodeF.setText("");
            telephoneF.setText("");
            area.setText("");
        }

    }

    private class SubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String messageDisplay = "";
            area.setText(messageDisplay);

            String numberEntered = accountNumberField.getText();
            String nameEntered = accountNameField.getText();
            String day = sortField1.getText();
            String month = sortField2.getText();
            String year = sortField3.getText();

            String date = "" + day + "/" + month + "/" + year;

            String fullName = title + nameEntered;

            //create variable for the address and asign to getText()
            String houseNumberEntered = numberF.getText();
            String streetNameEntered = streetF.getText();
            String townEntered = townF.getText();
            String postCodeEntered = postCodeF.getText();
            String telephoneEntered = telephoneF.getText();

            int an;

            if (numberEntered.length() == 0 || nameEntered.length() == 0
                    || houseNumberEntered.length() == 0
                    || streetNameEntered.length() == 0
                    || townEntered.length() == 0
                    || postCodeEntered.length() == 0
                    || telephoneEntered.length() == 0) {
                area.setText("Number, name and address details required for opening an account");
            } else if (numberEntered.length() != 8 || Integer.parseInt(numberEntered) < 0) {
                area.setText("Account number should be exact 8 digits and not negative number");
            } else {
                //two options for creating an account:
                if (classicButton.isSelected()) {
                    //create new bank account

                    String type = "Classic";
                    String overdraft = "0";
                    String feedback;

                    try {

                        String infoToSend = numberEntered + "," + date + "," + fullName + "," + type + "," + overdraft + "," + houseNumberEntered + ","
                                + streetNameEntered + "," + townEntered + "," + postCodeEntered + "," + telephoneEntered;

                        String detailsCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                        URL url = new URL("https://webbank-gvasilski.rhcloud.com/CreateAccont");
                        java.net.URLConnection connection = url.openConnection();
                        connection.setDoOutput(true);

                        OutputStreamWriter out = new OutputStreamWriter(
                                connection.getOutputStream());
                        out.write("string=" + detailsCodedString);
                        out.close();

                        //receive the feedback
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        connection.getInputStream()));
                        while ((feedback = in.readLine()) != null) {
                            area.setText(feedback);
                        }
                        in.close();

                    } catch (Exception ex) {
                        area.setText("ERROR: " + ex.getMessage());
                    }

                    //clear all the fields
                    accountNumberField.setText("");
                    accountNameField.setText("");
                    numberF.setText("");
                    streetF.setText("");
                    townF.setText("");
                    postCodeF.setText("");
                    telephoneF.setText("");
                    sortField1.setText("");
                    sortField2.setText("");
                    sortField3.setText("");
                    feedback = "";

                }

                if (goldenButton.isSelected()) {
                    //create new bank account
                    String type = "Golden";
                    String overdraft = "500.00";
                    String feedback;

                    try {

                        String infoToSend = numberEntered + "," + date + "," + fullName + "," + type + "," + overdraft + "," + houseNumberEntered + ","
                                + streetNameEntered + "," + townEntered + "," + postCodeEntered + "," + telephoneEntered;

                        String detailsCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                        URL url = new URL("https://webbank-gvasilski.rhcloud.com/CreateAccont");
                        java.net.URLConnection connection = url.openConnection();
                        connection.setDoOutput(true);

                        OutputStreamWriter out = new OutputStreamWriter(
                                connection.getOutputStream());
                        out.write("string=" + detailsCodedString);
                        out.close();

                        //receive the feedback
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        connection.getInputStream()));
                        while ((feedback = in.readLine()) != null) {
                            area.setText(feedback);
                        }
                        in.close();

                    } catch (Exception ex) {
                        System.out.println("ERROR: " + ex.getMessage());
                    }

                    //clear all the fields
                    accountNumberField.setText("");
                    accountNameField.setText("");
                    numberF.setText("");
                    streetF.setText("");
                    townF.setText("");
                    postCodeF.setText("");
                    telephoneF.setText("");
                    sortField1.setText("");
                    sortField2.setText("");
                    sortField3.setText("");
                    feedback = "";

                }
            }
            titleBox.setSelectedIndex(0);
        }
    }
}
