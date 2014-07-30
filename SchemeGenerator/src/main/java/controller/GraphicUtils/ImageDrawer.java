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
    public void drawOperatorElement(OperatorElementWrapper element){
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        if(element.getElementGraphicType().equals(ElementGraphicType.BLOCK)){
            drawBlock(element);
        }else if(element.getElementGraphicType().equals(ElementGraphicType.SIGNAL)){
            drawSignal(element);
        }
    }

    public void drawBlock(OperatorElementWrapper element){
        gc.setTextBaseline(VPos.CENTER);
        double x = element.getX();
        double y = element.getY();
        gc.strokeRect( x, y, CommonValues.LOGICAL_ELEMENT_WIDTH, CommonValues.LOGICAL_ELEMENT_HEIGHT);
        gc.fillText(element.getType().toNotLocaleString(), x + CommonValues.LOGICAL_ELEMENT_WIDTH /2, y + CommonValues.LOGICAL_ELEMENT_HEIGHT /2);
    }

    public void drawSignal(OperatorElementWrapper element){
        gc.setTextBaseline(VPos.BOTTOM);
        gc.setTextAlign(TextAlignment.LEFT);
        double x = element.getX();
        double y = element.getY();
        //padding 20
        x+=20;
        gc.fillText(element.getValue().toString(), x + CommonValues.LOGICAL_ELEMENT_WIDTH /2, y + CommonValues.LOGICAL_ELEMENT_HEIGHT /2);
        y+=CommonValues.LOGICAL_ELEMENT_HEIGHT/2;
        gc.strokeLine( x, y, x+CommonValues.LOGICAL_ELEMENT_WIDTH, y);
    }
}
