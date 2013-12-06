package View.Components;

import Controller.BaseController;
import Model.BaseModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/12/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class BaseView extends JComponent implements BaseViewInterface, Observer {
    protected BaseModel model;
    protected BaseController controller;
    protected GridBagLayout layout;

    @Override
    public void setLabels() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createControls() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
