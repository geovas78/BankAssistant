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
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * @version 08/07/2012
 * @author Gogo
 */
public class WithdrawPanel extends JPanel {

    DecimalFormat df = new DecimalFormat("#########0.00");

    private Font font1 = new Font("Dialog", Font.ITALIC, 15);
    private Font font2 = new Font("Dialog", Font.BOLD, 20);

    private JPanel bottomPanel, innerPanel, northPanel, centerPanel, southPanel;
    private JLabel message, accountLabel, amountLabel, dayLabel, monthLabel, yearLabel, info;
    private JTextField field, amountF, dayF, monthF, yearF;
    private JButton ok;
    Font f = new Font("Dialog", Font.ROMAN_BASELINE, 17);

    java.net.URL imageURL = WithdrawPanel.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog", Font.BOLD, 20);

    public WithdrawPanel() {
        setLayout(new BorderLayout());
        ok = new JButton("make WITHRAWAL");

        northPanel = new JPanel(new BorderLayout());

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //globalPanel = new JPanel(new BorderLayout());

        accountLabel = new JLabel("From Account number : ");
        accountLabel.setFont(f);
        amountLabel = new JLabel("Insert the amount   : £ ");
        amountLabel.setFont(f);
        field = new JTextField(8);
        amountF = new JTextField(18);

        accountPanel.add(accountLabel);
        accountPanel.add(field);

        amountPanel.add(amountLabel);
        amountPanel.add(amountF);

        northPanel.add("North", accountPanel);
        northPanel.add("South", amountPanel);

        message = new JLabel("");
        info = new JLabel("");

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(message);
        centerPanel.add(info);

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel empty = new JLabel("                           ");

        dayLabel = new JLabel("Date : day  ");
        monthLabel = new JLabel("month (in digits) ");
        yearLabel = new JLabel("year  ");
        dayF = new JTextField(2);
        monthF = new JTextField(2);
        yearF = new JTextField(4);
        southPanel.add(dayLabel);
        southPanel.add(dayF);
        southPanel.add(monthLabel);
        southPanel.add(monthF);
        southPanel.add(yearLabel);
        southPanel.add(yearF);
        southPanel.add(empty);
        southPanel.add(ok);

        innerPanel = new JPanel(new BorderLayout());
        innerPanel.add("North", northPanel);
        innerPanel.add("Center", southPanel);
        innerPanel.add("South", centerPanel);

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(new JLabel("@gogo"));

        JLabel banner = new JLabel("This is a WITHRAWAL window");
        Font fontBanner = new Font("Serif", Font.BOLD, 28);
        banner.setFont(fontBanner);
        banner.setForeground(Color.darkGray);
        JPanel bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bannerPanel.add(banner);

        JPanel real = new JPanel(new BorderLayout());
        //add all panels to the main panel
        real.setLayout(new BorderLayout());
        real.add("North", bannerPanel);
        real.add("Center", innerPanel);
        real.add("South", bottomPanel);

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

        ok.addActionListener(new OkListener());
    }

    public void clear() {
        field.setText("");
        amountF.setText("");
        info.setText("");

        dayF.setText("");
        monthF.setText("");
        yearF.setText("");
        message.setText("");
    }

    private class OkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            info.setText("");
            String numberEntered = field.getText();
            String amountEntered = amountF.getText();

            String dayEntered = dayF.getText();
            String monthEntered = monthF.getText();
            String yearEntered = yearF.getText();

            if (numberEntered.length() == 0 || amountEntered.length() == 0
                    || dayEntered.length() == 0 || monthEntered.length() == 0
                    || yearEntered.length() == 0) {
                message.setText("All field must be entered !!!");
                message.setFont(font2);
                message.setForeground(Color.red);
            } else if (numberEntered.length() != 8) {
                message.setText("Account number couln't exeed 8 digits");
            } else if (Integer.parseInt(numberEntered) < 0) {
                message.setText("Account number could NOT be a negative value");
            } else {

                if (Double.parseDouble(amountEntered) < 0) {
                    message.setText("Amount cannot be negative");
                    message.setFont(font2);
                    message.setForeground(Color.red);
                } else {
                    String date = "" + dayEntered + "/" + monthEntered + "/" + yearEntered;

                    try {

                        String infoToSend = numberEntered + "," + amountEntered + "," + date;

                        String infoCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                        URL url = new URL("https://webbank-gvasilski.rhcloud.com/MakeWithdraw");
                        java.net.URLConnection connection = url.openConnection();
                        connection.setDoOutput(true);

                        OutputStreamWriter out = new OutputStreamWriter(
                                connection.getOutputStream());
                        out.write("string=" + infoCodedString);
                        out.close();

                        //receive the feedback
                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                        connection.getInputStream()));
                        String feedback;
                        String ok = "OK";
                        while ((feedback = in.readLine()) != null) {
                            if (feedback.equals(ok)) {
                                message.setText("TRANSACTION WAS SUCCESSFUL");
                                message.setFont(font2);
                                message.setForeground(Color.green);
                            } else {
                                message.setText(feedback);
                                message.setFont(font2);
                                message.setForeground(Color.red);

                            }
                        }
                        in.close();
                    } catch (Exception ex) {
                        message.setText("ERROR: " + ex.getMessage());
                    }
                }
            }
        }

    }
}
