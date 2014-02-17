package view.components;

import controller.OutputController;
import javafx.scene.control.Button;
import model.OutputModel;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/12/13
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutputView extends BaseView {
    Button Btn;

    public OutputView (OutputModel argModel, OutputController argController){
        model = argModel;
        controller = argController;
        createControls();
    }



    @Override
    public void update(Observable o, Object arg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createControls() {
        initComponents();
        bindListeners();
        addToPane();
    }

    private void initComponents() {
        Btn =  new Button();
    }

    private void bindListeners() {

    }

    private void addToPane() {
        this.getChildren().add(Btn);
    }



//    @Override
//    public void setLabels() {
//        Btn.setText(Utils.getMessages().getString("remove"));
//    }
}
