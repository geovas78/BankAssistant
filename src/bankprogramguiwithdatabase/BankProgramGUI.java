/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankprogramguiwithdatabase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

/**
 *
 * @author Gogo
 */
public class BankProgramGUI extends JFrame implements ActionListener {

    private JMenuBar bar = new JMenuBar();
    private JMenu fileMenu = new JMenu("  Account Options  ");
    //menu item in the file menu
    private JMenuItem openAccount = new JMenuItem("Open an account");
    private JMenuItem deleteAccount = new JMenuItem("Close an account");
    private JMenuItem transactionHistory = new JMenuItem("Transaction history");
    private JMenuItem quit = new JMenuItem("QUIT");
    //search menu with two options
    private JMenu searchMenu = new JMenu("  Search  ");
    //items of search menu
    private JMenuItem oneAccountDisplay = new JMenuItem("Find an account");
    private JMenuItem displayAllAccounts = new JMenuItem("Display all accounts");
    //tools menu
    private JMenu toolMenu = new JMenu("  Tools  ");
    private JMenuItem changeStatus = new JMenuItem("Change account status");
    private JMenuItem transferMoney = new JMenuItem("Make money transfer");
    private JMenuItem depositMoney = new JMenuItem("Deposit");
    private JMenuItem withdrawMoney = new JMenuItem("Withdraw");
    // option menu
    private JMenu options = new JMenu("  Options  ");
    private JMenuItem setInterest = new JMenuItem("Set Interest Rate");
    private JMenuItem applyInterest = new JMenuItem("Apply Interest to all");
    //address menu
    private JMenu addressChange = new JMenu("  EDIT Address  ");
    private JMenuItem newAddress = new JMenuItem(" amend theaddress ");
    private JMenuItem changeHouseNumber = new JMenuItem("House number");
    private JMenuItem changeStreet = new JMenuItem("Street name");
    private JMenuItem changeTown = new JMenuItem("Town / City");
    private JMenuItem changePostCode = new JMenuItem("Post code");
    private JMenuItem changeTel = new JMenuItem("Telephone");

    //top panel with logo
    private JPanel topPanel = new JPanel();
    private JLabel label;
    java.net.URL imageURL = BankProgramGUI.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font = new Font("Dialog", Font.BOLD, 20);

    Font font33 = new Font("Dialog", Font.BOLD, 20);
    Font font34 = new Font("Dialog", Font.BOLD, 15);

    private OpenAccountPanel openAccountPanel = new OpenAccountPanel();
    private CloseAccountPanel closeAccountPanel = new CloseAccountPanel();
    private DisplayOneAccountPanel oneAccount = new DisplayOneAccountPanel();
    private TransactionHistoryPanel thpanel = new TransactionHistoryPanel();
    private MoneyTransferPanel mtp = new MoneyTransferPanel();
    private DepositPanel depositPanel = new DepositPanel();
    private WithdrawPanel withdrawPanel = new WithdrawPanel();
    private AddressChangePanel acp = new AddressChangePanel();
    private ChangeTypeOfAccount cta = new ChangeTypeOfAccount();

    //instance for the firlds in the change address panel
    private JTextField fieldNumber = new JTextField(8);
    private JTextField fieldName = new JTextField(20);
    private double interestFee;

    public BankProgramGUI() throws IOException, ClassNotFoundException {

        setTitle("Bank Program");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(700, 500);
        //setLocationRelativeTo(null);
        setLocation(50, 50);
        setLayout(new BorderLayout());

        fileMenu.add(openAccount);
        fileMenu.add(deleteAccount);
        fileMenu.addSeparator();
        fileMenu.add(transactionHistory);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        searchMenu.add(oneAccountDisplay);
        searchMenu.add(displayAllAccounts);

        toolMenu.add(changeStatus);
        toolMenu.add(transferMoney);
        toolMenu.addSeparator();
        toolMenu.add(depositMoney);
        toolMenu.add(withdrawMoney);

        options.add(setInterest);
        options.add(applyInterest);

        addressChange.add(newAddress);

        bar.setLayout(new BoxLayout(bar, BoxLayout.LINE_AXIS));
        bar.setFont(font33);

        bar.add(fileMenu);
        fileMenu.setFont(font34);
        //fileMenu.setBorder(new MatteBorder(new ImageIcon("dot.gif")));
        //fileMenu.setBorder(new LineBorder(Color.black));
        JLabel separator = new JLabel("||");
        JLabel separator1 = new JLabel("||");
        JLabel separator2 = new JLabel("||");
        JLabel separator3 = new JLabel("||");
        //separator.setBorder(new LineBorder(Color.black));
        bar.add(separator);
        separator.setForeground(Color.green);
        separator1.setForeground(Color.green);
        separator2.setForeground(Color.green);
        separator3.setForeground(Color.green);
        bar.add(searchMenu);
        searchMenu.setFont(font34);
        bar.add(separator1);
        bar.add(toolMenu);
        toolMenu.setFont(font34);
        bar.add(separator2);
        bar.add(options);
        options.setFont(font34);
        bar.add(separator3);
        bar.add(addressChange);

        setJMenuBar(bar);

        //create a toplayer of the window
        label = new JLabel();
        label.setIcon(icon);
        label.setText("GEORGE INVESTMENT BANK");
        label.setFont(font);

        //set up the top panel
        topPanel.setBorder(new LineBorder(Color.black));
        topPanel.add(label);
        add("North", topPanel);

        //Listeners for first menu
        openAccount.addActionListener(this);
        deleteAccount.addActionListener(this);
        transactionHistory.addActionListener(new FileListener());
        quit.addActionListener(new QuitListener());

        //listeners for the second menu
        oneAccountDisplay.addActionListener(this);
        displayAllAccounts.addActionListener(new SearchListener());

        transferMoney.addActionListener(new TransferListener());
        depositMoney.addActionListener(new DepositListener());
        withdrawMoney.addActionListener(new WithdrawListener());
        changeStatus.addActionListener(this);

        setInterest.addActionListener(new InterestSetUpListener());
        applyInterest.addActionListener(new ApplyInterestListener());

        newAddress.addActionListener(new ChangeAddressListener());

        //some empty labels
        JLabel empty1 = new JLabel("                     ");
        JLabel empty2 = new JLabel(getDate() + "   ");
        bar.add(empty1);
        bar.add(empty2);

        //time clock
        final JLabel timeLabel = new JLabel();
        bar.add(timeLabel);

        final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ActionListener timerListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                timeLabel.setText(time);
                timeLabel.setFont(font33);
            }
        };
        Timer timer = new Timer(1000, timerListener);
        // to make sure it doesn't wait one second at the start  
        timer.setInitialDelay(0);
        timer.start();

        JTextArea welcomeArea = new JTextArea(15, 40);
        String welcome = "WELCOME TO GOERGI'S BANK PROGRAM.\nIF YOU DON'T KNOW HOW TO USE THE SOFTWARE,\n"
                + "PLEASE CHECK WITH THE DEVELOPER ON www.georgi-vasilski.com";
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        welcomeArea.setText(welcome);
        welcomePanel.add(welcomeArea);
        //setContentPane(welcomePanel);
        add(welcomeArea, BorderLayout.CENTER);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openAccount) {
            setContentPane(openAccountPanel);
            setVisible(true);
        }
        if (e.getSource() == deleteAccount) {
            setContentPane(closeAccountPanel);
            setVisible(true);
        }
        if (e.getSource() == oneAccountDisplay) {
            oneAccount.clearFields();
            setContentPane(oneAccount);
            setVisible(true);
        }
        if (e.getSource() == changeStatus) {
            cta.clear();
            setContentPane(cta);
            setVisible(true);
        }
    }

    private class ChangeAddressListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String accountNumberEntered, message;
            message = "YOU WILL EXIT THE SETTINGS";

            try {
                accountNumberEntered = JOptionPane.showInputDialog(null,
                        "Please enter the account number below", null,
                        JOptionPane.PLAIN_MESSAGE);
                if (accountNumberEntered.length() != 0) {
                    acp.inputAccountNumber(accountNumberEntered);
                    acp.fieldsInput();
                    setContentPane(acp);
                    setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "You did't enter any account number !!!", null,
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, message, null,
                        JOptionPane.ERROR_MESSAGE);
                UIManager.put("OptionPane.messageForeground", Color.red);

                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Verdana", Font.BOLD, 32))
                );

            }
        }

    }

    private class ApplyInterestListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int answer = JOptionPane.showConfirmDialog(null, "Interest rate set to " + interestFee
                    + " % ", null,
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION) {
                String day = JOptionPane.showInputDialog(null, "Enter day of the date\n"
                        + "in DIGITS",
                        null, JOptionPane.PLAIN_MESSAGE);
                String month = JOptionPane.showInputDialog(null, "Enter month of the date\n"
                        + "in LETTERS",
                        null, JOptionPane.PLAIN_MESSAGE);
                String year = JOptionPane.showInputDialog(null, "Enter year of the date\n"
                        + "in DIGITS",
                        null, JOptionPane.PLAIN_MESSAGE);

                String date = "" + day + "/" + month + "/" + year;

                try {

                    String infoToSend = interestFee + "," + date;

                    String infoCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/InterestApply");
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
                    while ((feedback = in.readLine()) != null) {
                        System.out.println(feedback);
                    }
                    in.close();

                } catch (Exception ex) {
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
            if (answer == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Set another interest rate", null,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private class InterestSetUpListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String interestIn;
            try {

                interestIn = JOptionPane.showInputDialog(null, "Enter the interest rate",
                        null, JOptionPane.PLAIN_MESSAGE);

                if (interestIn.length() != 0) {
                    JOptionPane.showMessageDialog(null, "Interest rate set to " + interestIn
                            + " % ", null,
                            JOptionPane.INFORMATION_MESSAGE);
                    interestFee = Double.parseDouble(interestIn);

                } else {
                    JOptionPane.showMessageDialog(null, "You did't enter any interest rate", null,
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "You did't enter any interest rate", null,
                        JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private class DepositListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            depositPanel.clear();
            setContentPane(depositPanel);
            setVisible(true);
        }

    }

    private class WithdrawListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            withdrawPanel.clear();
            setContentPane(withdrawPanel);
            setVisible(true);
        }

    }

    private class TransferListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mtp.clear();
            setContentPane(mtp);
            setVisible(true);
        }

    }

    private class FileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            thpanel.clear();
            setContentPane(thpanel);
            setVisible(true);
        }

    }

    private class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == displayAllAccounts) {

                try {

                    String codedSent = URLEncoder.encode("info", "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/AllAccounts");
                    java.net.URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);

                    OutputStreamWriter out = new OutputStreamWriter(
                            connection.getOutputStream());
                    out.write("string=" + codedSent);
                    out.close();

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream()));
                    String decodedString;

                    String[] names = {"Account Number", "Account Holder Name", "Balance"};
                    String[][] data = {{"", "", ""}};

                    Table t1 = new Table(data, names);

                    while ((decodedString = in.readLine()) != null) {

                        System.out.println(decodedString);

                        String[] value = decodedString.split("[/]");

                        for (int i = 0; i < value.length; i++) {

                            String[] getDetail = value[i].split(("[,]"));
                            t1.addData(getDetail[0], getDetail[1], getDetail[2]);
                            t1.repaint();
                        }
                    }
                    setContentPane(t1);
                    setVisible(true);
                    in.close();

                } catch (Exception ex) {
                    ex.getMessage();
                }
            }
        }
    }

    private class QuitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int answer;
            answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit",
                    null,
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (answer == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }

    }

    //get the actual date
    public String getDate() {
        Calendar c = Calendar.getInstance();
        String d = new String();
        d = d.valueOf(c.get(Calendar.DATE) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR));
        return d;
    }

}
