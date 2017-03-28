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
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * @version 10/07/2012
 * @author Gogo
 */
public class AddressChangePanel extends JPanel
{
   private String numberOfAccount = null;
   
        //other containers and panels
    private JPanel mainPanel;
    private JPanel accountDataPanel;
    private JPanel accountType;
    private JPanel buttonPanel;
    private JPanel centralConsole;
    private JPanel topCentral;
    
    //attributes of AccountDataPanel
    private JLabel accountNumber,accountName;
    private JTextField accountNumberField, accountNameField,sortField1,
            sortField2,sortField3;
    private JComboBox titleBox;
    private String[] titles = {"-","MR.","MRS.","MISS"};
    private JPanel _1;
    private JPanel _2 ;
    
    //attributes of accountType
    private JRadioButton classicButton,goldenButton;
    
    //buttonPanel attributes
    private JButton submit, cancel;
    
    //centralConsole attributes
    private JPanel addressPanel, displayPanel,labelPanel,fieldPanel;
    private JTextArea area = new JTextArea(10,33);
    private JLabel number,street,town,postCode,telephone;
    private JTextField numberF,streetF,townF,postCodeF,telephoneF;
    
    
    java.net.URL imageURL = AddressChangePanel.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog",Font.BOLD,20);
   
   public AddressChangePanel()
   {
       setLayout(new BorderLayout());
         //implementing Panel within mainPanel
        //accountDataPanel
        accountDataPanel = new JPanel();
        
        
        accountNumber = new JLabel("Account  Number ");
        accountNumberField = new JTextField(8);
        accountNumberField.setEditable(false);
        //some additional info about the sort code
        JLabel sortCodeLabel = new JLabel("date");
        sortField1 = new JTextField(2);
        sortField1.setEditable(false);
        JLabel dash1 = new JLabel(" - ");
        JLabel dash2 = new JLabel(" - ");
        sortField2 = new JTextField(2);
        sortField3 = new JTextField(4);
        sortField2.setEditable(false);
        sortField3.setEditable(false);
        
        
        
        accountName = new JLabel("Account  Name " + "  ");
        accountNameField = new JTextField(20);
        accountNameField.setEditable(false);
        
        accountDataPanel.setLayout(new GridLayout(2,1));
        accountDataPanel.setBorder(new TitledBorder(new LineBorder(Color.black),"Account Information"));
        
        _1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        _2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        _1.add(accountNumber);
        _1.add(accountNumberField);
        _1.add(sortCodeLabel);
        _1.add(sortField1);
        _1.add(dash1);
        _1.add(sortField2);
        _1.add(dash2);
        _1.add(sortField3);
        _2.add(accountName);
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
        accountType.setBorder(new TitledBorder(new EtchedBorder(),"Account Type"));
        
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
        mainPanel.add(accountDataPanel,BorderLayout.CENTER);
        mainPanel.add(accountType,BorderLayout.EAST);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);
        
        //centralConsole
        centralConsole = new JPanel();
        
        labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(5,1));
        
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
        fieldPanel.setLayout(new GridLayout(5,1));
        
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
        addressPanel.setLayout(new GridLayout(1,2));
        addressPanel.add(labelPanel);
        addressPanel.add(fieldPanel);
        
        displayPanel = new JPanel(new BorderLayout());
        area.setText(" W E L L C O M E ");
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        displayPanel.add(scroll,BorderLayout.CENTER);
        
        centralConsole.setLayout(new BorderLayout());
        displayPanel.setBorder(new TitledBorder(new LineBorder(Color.black),"DISPLAY",
                TitledBorder.CENTER,TitledBorder.ABOVE_TOP));
        addressPanel.setBorder(new TitledBorder(new EtchedBorder(),"Address:"));
        centralConsole.add(addressPanel,BorderLayout.WEST);
        centralConsole.add(displayPanel,BorderLayout.CENTER);
        
        //topCentralPanel with two panels
        topCentral = new JPanel();
        topCentral.setLayout(new BorderLayout());
        topCentral.add(mainPanel,BorderLayout.NORTH);
        topCentral.add(centralConsole,BorderLayout.CENTER);
        
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
        add("North",_topPanel);
        add("Center",topCentral);
        
        submit.addActionListener(new SubmitListener());
        cancel.addActionListener(new CancelListener());
        
        
   }
   
   public void fieldsInput()
   {
       DecimalFormat df = new DecimalFormat("######0.00");
       
       try
       {
           String accountCodedSent = URLEncoder.encode(numberOfAccount, "UTF-8");

                    URL url = new URL("https://webbank-gvasilski.rhcloud.com/RetriveAddress");
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
                    while ((decodedString = in.readLine()) != null) {
                        
                        String[] value = decodedString.split("[,]");

                         accountNumberField.setText(value[0]);
                         area.setText("Account was open on :" + value[1]);
                         accountNameField.setText(value[2]);
                         numberF.setText(value[3]);
                         streetF.setText(value[4]);
                         townF.setText(value[5]);
                         postCodeF.setText(value[6]);
                         telephoneF.setText("0" + value[7]);
                         
                    }
       }
       catch(Exception ex)
       {
           ex.getMessage();
       }
   }
   
   public void inputAccountNumber(String accountNumberEntered)
   {
       //asign the velue
       numberOfAccount = accountNumberEntered;
   }
   
    private class SubmitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            //create variable for the address and asign to getText()
            String houseNumberEntered = numberF.getText();
            String streetNameEntered = streetF.getText();
            String townEntered = townF.getText();
            String postCodeEntered = postCodeF.getText();
            String telephoneEntered = telephoneF.getText();
            
            
            if(houseNumberEntered.length() == 0 ||
                    streetNameEntered.length() == 0 ||
                    townEntered.length() == 0 ||
                    postCodeEntered.length() == 0 ||
                    telephoneEntered.length() == 0)
            {
                area.setText("     Address details required     ");
            }
            else
            {
              
              try
              {
                  
                  String infoToSend = houseNumberEntered + "," + streetNameEntered + "," + townEntered + "," + 
                          postCodeEntered + "," + telephoneEntered + "," + numberOfAccount;

                        String detailsCodedString = URLEncoder.encode(infoToSend, "UTF-8");

                        URL url = new URL("https://webbank-gvasilski.rhcloud.com/SetNewAddress");
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
                        String feedback;
                        while ((feedback = in.readLine()) != null) {
                            area.setText(feedback);
                        }
                        in.close();
                  
              }
              catch(Exception ex)
              {
                   area.setText("ERROR: " + ex.getMessage());
              }
                
                accountNumberField.setText("");
                accountNameField.setText("");
                numberF.setText("");
                streetF.setText("");
                townF.setText("");
                postCodeF.setText("");
                telephoneF.setText("");
            }
        }
        
    }
    
    private class CancelListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            numberF.setText("");
            streetF.setText("");
            townF.setText("");
            postCodeF.setText("");
            telephoneF.setText("");
            area.setText("");
        }
        
    }
}
