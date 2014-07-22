package controller;

import controller.utils.CommonValues;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import model.equationModel.OperatorEquationModel;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class SchemeImageBuilder {
    private Canvas canvas = new Canvas(CommonValues.CANVAS_WIDTH, CommonValues.CANVAS_HEIGHT);

    public Image buildImage(OperatorEquationModel model){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GOLD);
        gc.fillRect(0, 0, CommonValues.CANVAS_WIDTH, CommonValues.CANVAS_HEIGHT);
        WritableImage writableImage = new WritableImage((int)CommonValues.CANVAS_WIDTH, (int)CommonValues.CANVAS_HEIGHT);
        canvas.snapshot(null, writableImage);
        return writableImage;
    }
}
