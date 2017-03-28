/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankprogramguiwithdatabase;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Gogo
 */
class Table extends JPanel
{
    DecimalFormat df = new DecimalFormat("#########0.00");
    NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.UK);
    private Table.MyModel model;

    java.net.URL imageURL = Table.class.getResource("images.gif");
    private ImageIcon icon = new ImageIcon(imageURL);
    Font font99 = new Font("Dialog",Font.BOLD,20);

    public Table(Object[][] data, String[] columns) 
    {
        JPanel panel = new JPanel(new BorderLayout());
	// constructor of JTable model
	model = new Table.MyModel(data, columns);
	// the table from that model
	JTable table = new JTable(model);
        panel.add(new JScrollPane(table));
	
       setLayout(new BorderLayout());
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
        add("Center",panel);
        
        
    }
    
    public void addData(String number, String name, String balance)
    {
        model.add(number, name, balance);
    }
    
    private class MyModel extends AbstractTableModel 
            {
	 
	        // to store our elements it will be great to avoid parallel array and use
	        // an ArrayList<Animal> but for simplicity and not to have to add a new
	        // class with will use an ArrayList<Object> for each row
	        ArrayList<Object[]> list;
	        // the headers
	        String[] header;
	         
	        // constructor
	        MyModel(Object[][] obj, String[] header) {
	            // save the header
	            this.header = header;  
	            // and the rows
	            list = new ArrayList<Object[]>();
	            // copy the rows into the ArrayList
	            for(int i = 0; i < obj.length; ++i)
	                list.add(obj[i]);
	        }
	        // method that needs to be overload. The row count is the size of the ArrayList
                @Override
	        public int getRowCount() {
	            return list.size();
	        }
	 
	        // method that needs to be overload. The column count is the size of our header
                @Override
	        public int getColumnCount() {
	            return header.length;
	        }
	 
	        // method that needs to be overload. The object is in the arrayList at rowIndex
                @Override
	        public Object getValueAt(int rowIndex, int columnIndex) {
	            return list.get(rowIndex)[columnIndex];
	        }
	         
	        // a method to return the column name
                @Override
	        public String getColumnName(int index) {
	            return header[index];
	        }
	         
	        // a method to add a new line to the table
	        void add(String number, String name,String balance) {
	            // make it an array[2] as this is the way it is stored in the ArrayList
	            // (not best design but we want simplicity)
	            String[] str = new String[3];
	            str[0] = number;
	            str[1] = name;
                    str[2] = "£" + balance;
	            list.add(str);
	            // inform the GUI that I have change
	            model.fireTableDataChanged();
	        }
	    }
    
}