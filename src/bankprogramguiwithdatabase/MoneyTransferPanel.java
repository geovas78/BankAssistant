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
import java.util.Calendar;
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
public class MoneyTransferPanel extends JPanel {

    static DecimalFormat df = new DecimalFormat("######0.00");

    private JPanel bottomPanel, innerPanel, northPanel, centerPanel, southPanel;
    private JLabel message, accountLabel, toLabel, amountLabel, dayLabel, monthLabel, yearLabel;
    private JTextField fromField, toField, amountF, dayF, monthF, yearF;
    private JButton ok;
    Font f = new Font("Dialog", Font.ROMAN_BASELINE, 17);

    java.net.URL imageURL = MoneyTransferPanel.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog", Font.BOLD, 20);
    private Font font2 = new Font("Dialog", Font.BOLD, 15);

    private double POSTbalance1;
    private double POSTbalance2;

    public MoneyTransferPanel() {
        setLayout(new BorderLayout());
        ok = new JButton("make TRANSFER");

        northPanel = new JPanel(new BorderLayout());

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //globalPanel = new JPanel(new BorderLayout());
        JLabel e = new JLabel("                              ");
        accountLabel = new JLabel("From Account number : ");
        accountLabel.setFont(f);
        toLabel = new JLabel("To Account number : ");
        toLabel.setFont(f);
        toField = new JTextField(8);
        amountLabel = new JLabel("Insert the amount   : £ ");
        amountLabel.setFont(f);
        fromField = new JTextField(8);
        amountF = new JTextField(18);

        accountPanel.add(accountLabel);
        accountPanel.add(fromField);
        accountPanel.add(e);
        accountPanel.add(toLabel);
        accountPanel.add(toField);

        amountPanel.add(amountLabel);
        amountPanel.add(amountF);

        northPanel.add("North", accountPanel);
        northPanel.add("South", amountPanel);

        message = new JLabel("");

        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(message);

        southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel empty = new JLabel("                           ");

        dayLabel = new JLabel("Date : day  ");
        dayLabel.setVisible(false);
        monthLabel = new JLabel("month (in digits) ");
        monthLabel.setVisible(false);
        yearLabel = new JLabel("year  ");
        yearLabel.setVisible(false);
        dayF = new JTextField(2);
        dayF.setVisible(false);
        monthF = new JTextField(2);
        monthF.setVisible(false);
        yearF = new JTextField(4);
        yearF.setVisible(false);
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

        JLabel banner = new JLabel("This is a TRANSFER window");
        Font fontBanner = new Font("Serif", Font.BOLD, 28);
        banner.setFont(fontBanner);
        banner.setForeground(Color.darkGray);
        JPanel bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bannerPanel.add(banner);

        //add all panels to the main panel
        JPanel real = new JPanel(new BorderLayout());
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
        fromField.setText("");
        toField.setText("");
        amountF.setText("");

        dayF.setText("");
        monthF.setText("");
        yearF.setText("");
        message.setText("");
    }

    public String getDate() {
        Calendar c = Calendar.getInstance();
        String d = new String();
        d = d.valueOf(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
        return d;
    }

    private class OkListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountWithdraw = fromField.getText();
            String accountDeposit = toField.getText();
            String amountEntered = amountF.getText();

            if ((accountWithdraw.length() == 0 || accountDeposit.length() == 0) || amountEntered.length() == 0) {
                message.setText("All field must be entered !!!");
                message.setFont(font2);
                message.setForeground(Color.red);
            } else if (accountWithdraw.length() != 8 || accountDeposit.length() != 8) {
                message.setText("Account number must be exact 8 digits !!!");
                message.setFont(font2);
                message.setForeground(Color.red);
            } else if (Integer.parseInt(accountWithdraw) < 0 || Integer.parseInt(accountDeposit) < 0) {
                message.setText("Account number could NOT be a negative value");
            } else if(Double.parseDouble(amountEntered) < 0){
                message.setText("Amount could NOT be a negative value");
            }
            else {

                try {

                    String infoToSend = accountDeposit + "," + accountWithdraw + "," + amountEntered + "," + getDate();

                    String infoCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/TransferMoney");
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
                    message.setText(ex.getMessage());
                }
            }
        }
    }
}
