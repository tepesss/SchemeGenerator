package controller.builder;

import controller.GraphicUtils.ImageDrawer;
import controller.utils.CommonValues;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.AppContext;
import model.OutputModel;
import model.equationModel.ElementsType;
import model.equationModel.OperatorElement;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class SchemeImageBuilder {
    private OutputModel model;
    private Canvas canvas;
    private GraphicsContext gc;
    private ImageDrawer drawer;

    public void buildImage(Canvas canvas){
        model = (OutputModel) AppContext.getInstance().getModels().get(OutputModel.class.getName());
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CommonValues.CANVAS_WIDTH, CommonValues.CANVAS_HEIGHT);
        drawer = new ImageDrawer(gc);
        OperatorElement element = new OperatorElement();
        element.setType(ElementsType.F);
        drawer.drawOperatorElement(element, 0, 0);
    }



    private void drawSignalElement(String text, double x, double y){

    }



}
