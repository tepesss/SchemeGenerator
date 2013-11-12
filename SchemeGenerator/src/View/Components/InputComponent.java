package View.Components;

import Controller.Utils.Utils;
import Model.AppContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
public class InputComponent extends BaseComponent implements ActionListener  {
    JButton generateBtn;
    JSpinner xSpinner;
    JSpinner ySpinner;
    int spinerMaxValue = 10;
    ChangeListener spinersChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            if(e.getSource() == xSpinner){
                System.out.println("xSpinner " + xSpinner.getValue());
            }  else if(e.getSource() == ySpinner){
                System.out.println("ySpinner " + ySpinner.getValue());
            }
        }
    };

    public void setLabels(){
        generateBtn.setText(Utils.getMessages().getString("generate"));
    }

    @Override
    public void createControls() {
        generateBtn = new JButton();
        xSpinner = new JSpinner(new SpinnerNumberModel(1, 1, spinerMaxValue, 1));
        ySpinner = new JSpinner(new SpinnerNumberModel(1, 1, spinerMaxValue, 1));
        xSpinner.addChangeListener(spinersChangeListener);
        ySpinner.addChangeListener(spinersChangeListener);
        generateBtn.addActionListener(this);
        setLayout(new GridBagLayout());
        add(generateBtn);
        add(xSpinner);
        add(ySpinner);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() ==  generateBtn){
           AppContext.getInstance().getMainScreenController().generateScheme();
       }
    }
}
