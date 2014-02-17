package controller;

import controller.utils.Utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.AppContext;
import model.OutputModel;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreenController extends BaseController {
    private BorderPane view;
    private AppContext model;

    public MainScreenController(AppContext model) {
        this.model = model;
        view = ((BorderPane) model.getRootPane());
        setCurrentScreen(getInputScreen());
    }

    private void setCurrentScreen(Pane paneToSet){
        ((BorderPane) model.getRootPane()).setCenter(paneToSet);
    }

    public void nextScreen(){
        setCurrentScreen(getOutputScreen());
    }

    @Override
    public Pane getView() {
        return view;
    }

    public Pane getInputScreen() {
        return loadPaneFXML("/fxml/InputView.fxml");
    }

    public Pane loadPaneFXML(String fxmlPath) {
        Pane pane = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Utils.getMessages());
            pane = (Pane) loader.load(getClass().getResource(fxmlPath).openStream());
            InputController controller = loader.getController();
            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    public Pane getOutputScreen() {
        OutputModel outputModel = new OutputModel();
        OutputController outputController = new OutputController(outputModel);
        return outputController.getView();
    }

}
