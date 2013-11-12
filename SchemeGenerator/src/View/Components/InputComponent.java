package View.Components;

import Controller.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/12/13
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputComponent extends BaseComponent implements ActionListener {
    JLabel jLabel;
    JButton addBtn;
    JButton removeBtn;


    public void setLabels(){
        jLabel.setText(Utils.getMessages().getString("generate"));
        addBtn.setText(Utils.getMessages().getString("add"));
        removeBtn.setText(Utils.getMessages().getString("remove"));
    }

    @Override
    public void createControls() {
        jLabel = new JLabel();
        addBtn = new JButton();
        removeBtn = new JButton();
        addBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        setLayout(new GridBagLayout());
        add(jLabel);
        add(addBtn);
        add(removeBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
