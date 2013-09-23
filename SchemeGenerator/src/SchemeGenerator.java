import View.MainScreen;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class SchemeGenerator {
    public static void main (String[] args){
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                new MainScreen().show();
            }
        });
    }
}
