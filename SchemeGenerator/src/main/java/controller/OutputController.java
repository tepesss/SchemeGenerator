package controller;

import controller.utils.Utils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.OutputModel;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/31/13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutputController implements IBaseController, EventHandler<MouseEvent> {
    private OutputModel model;
    @FXML
    private Canvas schemeCanvas;
    @FXML
    private Button saveBtn;
    @FXML
    private Button printBtn;

    @Override
    public void init() {
        /*
        model = (OutputModel) AppContext.getInstance().getModels().get(model.getClass().getName());
        System.out.println(model);
        */
        saveBtn.setOnMouseClicked(this);
        printBtn.setOnMouseClicked(this);
        GraphicsContext gc = schemeCanvas.getGraphicsContext2D();
        gc.setFill(Color.AQUA);
        gc.fillRect(0, 0, schemeCanvas.getWidth(), schemeCanvas.getHeight());
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        if (mouseEvent.getSource().equals(printBtn)) {
        } else if (mouseEvent.getSource().equals(saveBtn)) {
            WritableImage writableImage = new WritableImage((int)schemeCanvas.getWidth(), (int)schemeCanvas.getHeight());
            schemeCanvas.snapshot(null, writableImage);
            Utils.saveToFile(writableImage);
        }
    }
}
