package view;

import controller.MainScreenController;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.AppContext;
import view.components.BaseViewInterface;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/31/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreen extends StackPane implements BaseViewInterface, Observer {

    Pane currentComponent;
    AppContext model;
    MainScreenController controller;

    public MainScreen(AppContext argModel, MainScreenController argController) {
        super();
        model = argModel;
        model.addObserver(this);
        controller = argController;
        createControls();
    }


    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public void createControls() {
        currentComponent = controller.getInputScreen();
        this.getChildren().add(currentComponent);
    }

    public void setCurrentComponent(Pane argView){
        getChildren().remove(currentComponent);
        currentComponent = argView;
        getChildren().add(currentComponent);

    }


}