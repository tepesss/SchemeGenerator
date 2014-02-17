package view.components;

import controller.BaseController;
import model.BaseModel;
import javafx.scene.layout.Pane;

import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/12/13
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseView extends Pane implements BaseViewInterface, Observer {
    protected BaseModel model;
    protected BaseController controller;
}
