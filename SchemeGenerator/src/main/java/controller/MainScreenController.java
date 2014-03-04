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
public class MainScreenController implements IBaseController {
    private AppContext model;

    public MainScreenController(AppContext model) {
        this.model = model;
        setCurrentScreen(getInputScreen());
    }

    private void setCurrentScreen(Pane paneToSet){
        ((BorderPane) model.getRootPane()).setCenter(paneToSet);
    }

    public void nextScreen(){
        setCurrentScreen(getOutputScreen());
    }

    @Override
    public void init() {
    }

    public Pane loadPaneFXML(String fxmlPath) {
        Pane pane = null;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(Utils.getMessages());
            pane = (Pane) loader.load(getClass().getResource(fxmlPath).openStream());
            IBaseController controller = loader.getController();
            controller.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    public Pane getInputScreen() {
        return loadPaneFXML("/fxml/inputView.fxml");
    }

    public Pane getOutputScreen() {
        return loadPaneFXML("/fxml/outputView.fxml");
    }

}
