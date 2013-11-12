package Controller;

import View.MainFrame;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreenController {
    private MainFrame view;

    public void start() {
        view = new MainFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.repaint();
            }
        });
    }
    public void generateScheme(){
        view.showOutputScreen();
    }

}
