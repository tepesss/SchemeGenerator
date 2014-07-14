import controller.utils.CommonValues;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.AppContext;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class SchemeGenerator extends Application {
    private Pane root;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rootView.fxml"));
        this.root = (Pane) loader.load();
        createAppContext();
        Scene scene = new Scene(root);
        stage.setHeight(CommonValues.STAGE_HEIGHT);
        stage.setWidth(CommonValues.STAGE_WIDTH);
        stage.setScene(scene);
        stage.show();
    }

    private void createAppContext(){
        AppContext app = AppContext.getInstance();
        app.setRootPane(root);
    }

    public static void main(String[] args){
        launch(args);
    }
}
