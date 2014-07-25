package controller.builder;

import controller.utils.CommonValues;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.AppContext;
import model.OutputModel;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class SchemeImageBuilder {
    private OutputModel model;
    private Canvas canvas;
    private GraphicsContext gc;
    public void buildImage(Canvas canvas){
        model = (OutputModel) AppContext.getInstance().getModels().get(OutputModel.class.getName());
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CommonValues.CANVAS_WIDTH, CommonValues.CANVAS_HEIGHT);
        drawLogicalElement("F", 0, 0);
    }

    private void drawLogicalElement(String text, double x, double y){
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        gc.strokeRect( x, y, CommonValues.LOGICAL_ELEMENT_WIDTH, CommonValues.LOGICAL_ELEMENT_HEIGHT);
        gc.fillText(text, x + CommonValues.LOGICAL_ELEMENT_WIDTH /2, y + CommonValues.LOGICAL_ELEMENT_HEIGHT /2);
    }

}
