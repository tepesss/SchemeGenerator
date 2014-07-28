package controller.GraphicUtils;

import controller.utils.CommonValues;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.equationModel.OperatorElement;

/**
 * Created by Volodymyr_Kychak on 7/28/14.
 */
public class ImageDrawer {
    private GraphicsContext gc;
    public ImageDrawer(GraphicsContext gc) {
        this.gc = gc;
    }
    public void drawOperatorElement(OperatorElement element, double x, double y){
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        gc.strokeRect( x, y, CommonValues.LOGICAL_ELEMENT_WIDTH, CommonValues.LOGICAL_ELEMENT_HEIGHT);
        gc.fillText(element.getType().toNotLocaleString(), x + CommonValues.LOGICAL_ELEMENT_WIDTH /2, y + CommonValues.LOGICAL_ELEMENT_HEIGHT /2);
    }
}
