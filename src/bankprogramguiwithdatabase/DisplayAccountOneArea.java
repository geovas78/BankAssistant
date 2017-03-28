/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankprogramguiwithdatabase;

import java.text.DecimalFormat;
import javax.swing.JPanel;

/**
 *
 * @author George
 */
public class DisplayAccountOneArea extends JPanel
{
    private int accountNumber;
    private String openningDate;
    private String accountName;
    private String type;
    private double overdraft;
    private String houseNumber;
    private String street;
    private String town;
    private String postCode;
    private String telephone;
    private double balance;
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel accountLabel;
    private javax.swing.JLabel accountNameLabel;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JLabel houseNumberLabel;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel openAccountLabel;
    private javax.swing.JLabel overdraftLabel;
    private javax.swing.JLabel postCodeLabel;
    private javax.swing.JLabel streetName;
    private javax.swing.JLabel telephoneLabel;
    private javax.swing.JLabel townLabel;
    // End of variables declaration 
    
    DecimalFormat df = new DecimalFormat("######0.00");
    
    
    public DisplayAccountOneArea(int account, String openDate, String holderName, String type, double overdraft, String houseNumber,
            String street, String town, String postCode, String telephone, double balance)
    {
        accountNumber = account;
        openningDate = openDate;
        accountName = holderName;
        this.type = type;
        this.overdraft = overdraft;
        this.houseNumber = houseNumber;
        this.street = street;
        this.town = town;
        this.postCode = postCode;
        this.telephone = telephone;
        this.balance = balance;
        
        initComponents();
    }

    @SuppressWarnings("unchecked")                         
    private void initComponents() {

        accountLabel = new javax.swing.JLabel();
        openAccountLabel = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        overdraftLabel = new javax.swing.JLabel();
        accountNameLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        houseNumberLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        streetName = new javax.swing.JLabel();
        townLabel = new javax.swing.JLabel();
        postCodeLabel = new javax.swing.JLabel();
        telephoneLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(700, 300));

        accountLabel.setText("Account  Number : " + accountNumber + " ( type : " + type + ")");

        openAccountLabel.setText("opened on : " + openningDate);

        balanceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        balanceLabel.setText("Actual balance : £ " + df.format(balance));

        overdraftLabel.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        overdraftLabel.setText("overdraft available : £ " + df.format(overdraft));

        accountNameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        accountNameLabel.setText(accountName);

        nameLabel.setText("Account holder's name:");

        addressLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addressLabel.setText("Billing and mail address :");

        houseNumberLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        houseNumberLabel.setText(houseNumber);

        jLabel8.setText("");

        streetName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        streetName.setText(street);

        townLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        townLabel.setText(town);

        postCodeLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        postCodeLabel.setText(postCode);

        telephoneLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        telephoneLabel.setText("telephone : 0" + telephone);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(overdraftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                                .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(openAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(houseNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(streetName, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(townLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(postCodeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(telephoneLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(accountNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(accountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(overdraftLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(streetName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(houseNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(townLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(postCodeLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telephoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }
}
