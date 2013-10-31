package View;

import Controller.Utils.LocaleManager;
import Controller.Utils.Utils;
import Model.AppContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/31/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainView extends JFrame {
    static JFrame jfrm;
    static JLabel jLabel;

    public void showContent(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initView();
            }
        });
    }



    private void initView(){
        jfrm = new JFrame();
        jfrm.setTitle(Utils.getMessages().getString("appName"));
        jfrm.setSize(275, 100);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLayout(new FlowLayout());
        jLabel = new JLabel(Utils.getMessages().getString("generate"));
        JButton addBtn = new JButton(Utils.getMessages().getString("add"));
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invalidateLables();
                AppContext.getInstance().setCurrentLocale(LocaleManager.ENGLISH);
                jLabel.setText(Utils.getMessages().getString("generate"));
            }
        });
        JButton removeBtn = new JButton(Utils.getMessages().getString("remove"));
        jfrm.add(jLabel);
        jfrm.add(addBtn);
        jfrm.add(removeBtn);
        jfrm.setVisible(true);
    }

    protected void invalidateLables(){

    }
}
