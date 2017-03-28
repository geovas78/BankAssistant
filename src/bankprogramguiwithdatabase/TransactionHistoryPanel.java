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
public class TransactionHistoryPanel extends JPanel {

    private JEditorPane area = new JEditorPane("text/html", "");
    JScrollPane p;
    JPanel real;
    private JLabel promptInfo = new JLabel("Enter account number ");
    private JTextField field = new JTextField(5);
    private JButton button = new JButton("SHOW");
    private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    java.net.URL imageURL = TransactionHistoryPanel.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog", Font.BOLD, 20);

    //private String addData = "";
    public TransactionHistoryPanel() {
        setLayout(new BorderLayout());

        panel.add(promptInfo);
        panel.add(field);
        panel.add(button);

        area.setEditable(false);
        p = new JScrollPane(area);
        //area.setEditable(false);
        //add(p);

        add(panel, BorderLayout.NORTH);
        add(p, BorderLayout.CENTER);

        real = new JPanel(new BorderLayout());
        real.add(panel, BorderLayout.NORTH);
        real.add(p, BorderLayout.CENTER);

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

    public void clear() {
        area.setText("");
        field.setText("");
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String allInfo = "";
            area.setText(allInfo);

            String accountNumberEntered = field.getText();

            if (accountNumberEntered.length() == 0) {
                area.setText("Account number required for seeing transations");
            } else if (Integer.parseInt(accountNumberEntered) < 0) {
                area.setText("Account number could NOT be a negative value");
            } else if (accountNumberEntered.length() != 8) {
                area.setText("account number should be exact 8 digits");
            } else {

                String header = "<head>"
                        + "<style>\n"
                        + "body{\n"
                        + "	background-color:#000;\n"
                        + "}\n"
                        + "\n"
                        + "th{\n"
                        + "	font-family:\"Times New Roman\", Times, serif;\n"
                        + "	font-weight:bold;\n"
                        + "	font-size:11px;\n"
                        + "	color:white;\n"
                        + "	padding-right:20px;\n"
                        + "}\n"
                        + "td{\n"
                        + "	font-family:\"Courier New\", Courier, monospace;\n"
                        + "	text-align:center;\n"
                        + "	font-size:14px;\n"
                        + "	color:white;\n"
                        + "	padding-right:10px;\n"
                        + "}\n"
                        + "span {\n"
                        + "	color:green;\n"
                        + "}\n"
                        + "\n"
                        + "</style>"
                        + "</head><body>"
                        + "<table>\n"
                        + "            <tbody>\n"
                        + "            <thead >\n"
                        + "              <tr>\n"
                        + "                <th>-          Date          -</th>\n"
                        + "                <th>-          Type          -</th>\n"
                        + "                <th>-          Amount          -</th>\n"
                        + "                <th>-          prior Balance          -</th>\n"
                        + "                <th>-          Actual BALANCE          -</th>\n"
                        + "              </tr>\n"
                        + "            </thead>\n"
                        + "            <tbody>";
                String footer = "</tbody>\n"
                        + "          </table></body>";
                String columnBegin = "<tr>";
                String columnEnd = "</tr>";
                String cellStart = "<td>";
                String cellEnd = "</td>";
                String spanStart = "<span ";
                String spanGreen = "style = \"color:green\">";
                String spanRed = "style = \"color:red\">";
                String spanEnd = "</span>";

                try {
                    String accountCodedSent = URLEncoder.encode(accountNumberEntered, "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/TransactionsShow");
                    java.net.URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write("string=" + accountCodedSent);
                    out.close();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));
                    String decodedString;
                    String addData = "";
                    while ((decodedString = in.readLine()) != null) {
                        System.out.println(decodedString);
                        String[] value = decodedString.split("[&]");
                        String spanColor;
                        for (int i = 0; i < value.length; i++) {
                            String[] getDetail = value[i].split(("[,]"));

                            addData = addData + columnBegin
                                    + cellStart + getDetail[0] + cellEnd
                                    + cellStart + getDetail[1] + cellEnd
                                    + cellStart + "£" + getDetail[2] + cellEnd
                                    + cellStart + "£" + getDetail[3] + cellEnd;
                            double balanceConvert = Double.parseDouble(getDetail[3]);
                            if (balanceConvert < 0.00) {
                                spanColor = spanRed;
                            } else {
                                spanColor = spanGreen;
                            }

                            String balance = cellStart + spanStart + spanColor + "£" + getDetail[4]
                                    + spanEnd + columnEnd;
                            addData = addData + balance;
                        }

                        allInfo = header + addData + footer;
                        area.setText(allInfo);
                        addData = "";
                    }
                    in.close();
                } catch (Exception ex) {
                    area.setText(ex.getMessage());
                }

            }
        }

    }
}
