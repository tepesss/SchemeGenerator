package View.Components;

import Controller.OutputController;
import Model.OutputModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/12/13
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutputView extends BaseView implements ActionListener {

    public OutputView (OutputModel argModel, OutputController argController){
        model = argModel;
        controller = argController;
    }

    @Override
    public void setLabels() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createControls() {
        layout = new GridBagLayout();
        setLayout(layout);
        add(new JButton("hklasdjfhkasjhdfkljashd"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
