package View;

import Controller.Utils.Utils;

import javax.swing.*;
import java.util.ResourceBundle;


/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreen {
    public static void show(){
        final ResourceBundle   bundle = Utils.getMessages();
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                JFrame jfrm = new JFrame();
                jfrm.setTitle(bundle.getString("appName"));
                jfrm.setSize(275, 100);
                jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel jLabel = new JLabel(bundle.getString("generate"));
                jfrm.add(jLabel);
                jfrm.setVisible(true);
            }
        });
    }
}
