/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bankprogramguiwithdatabase;


import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 *
 * @author BIG JOE
 */
public class BankProgramGUIWithDatabase 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        
        try {
            String lnf = null;
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equalsIgnoreCase(info.getName())) {
                    lnf = info.getClassName();
                    UIManager.setLookAndFeel(lnf);
                    break;
                }
            }
            if (lnf == null) {
                 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        
        new BankProgramGUI();
    }
}
