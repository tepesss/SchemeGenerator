package View;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreen {
    public void show(){
        JFrame jfrm = new JFrame("A simple Swing Aplication");
        jfrm.setSize(275, 100);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel jLabel = new JLabel("How to create new GUI with Swing?");
        jfrm.add(jLabel);
        jfrm.setVisible(true);
    }
}
