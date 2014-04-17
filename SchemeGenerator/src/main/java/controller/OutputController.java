package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.OutputModel;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/31/13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutputController implements IBaseController{
    private OutputModel model;
    @FXML
    private Canvas schemeCanvas;

    @Override
    public void init() {
        GraphicsContext gc = schemeCanvas.getGraphicsContext2D();
        gc.setFill(Color.AQUA);
        gc.fillRect(0, 0, schemeCanvas.getWidth(), schemeCanvas.getHeight());
    }

}
