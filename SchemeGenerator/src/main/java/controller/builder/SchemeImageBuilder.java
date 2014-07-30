package controller.builder;

import controller.GraphicUtils.ImageDrawer;
import controller.GraphicUtils.OperatorElementWrapper;
import controller.utils.CommonValues;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import model.AppContext;
import model.OutputModel;
import model.combinedTable.CellsCalculatedValue;
import model.equationModel.*;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class SchemeImageBuilder {
    private OutputModel model;
    private OperatorEquationModel equation;
    private Canvas canvas;
    private GraphicsContext gc;
    private ImageDrawer drawer;
    private LinkedList<OperatorElementWrapper> drawnElements = new LinkedList<>();

    public void buildImage(Canvas canvas) {
        model = (OutputModel) AppContext.getInstance().getModels().get(OutputModel.class.getName());
        equation = model.getEquationModel();
        initGraphics(canvas);
        drawEquation();
    }

    private void initGraphics(Canvas canvas) {
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, CommonValues.CANVAS_WIDTH, CommonValues.CANVAS_HEIGHT);
        drawer = new ImageDrawer(gc);
    }

    private void drawEquation() {
        drawInputs();
        drawOperators();
        drawOutputs();
    }

    private void drawInputs() {
        InputEquation input = equation.getInput();
        LinkedList<OperatorElementWrapper> inputsList = wrapElements(input.getOperatorElements());
        double x = 40;
        double y = 20;
        for (OperatorElementWrapper elementWrapper: inputsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            y+=50;
        }
        LinkedList<OperatorElementWrapper> supplSignalList =  wrapElements(input.getSubListByType(ElementsType.SUPPLEMENTARY_SIGNALS));
    }

    private LinkedList<OperatorElementWrapper> wrapElements(LinkedList<OperatorElement> operatorList){
        LinkedList<OperatorElementWrapper> list = new LinkedList<>();
        for(OperatorElement element : operatorList){
            list.add(OperatorElementWrapper.wrap(element));
        }
        return list;
    }

    private void drawOperators() {
        OperatorEquation operator = equation.getOperator();
    }

    private void drawOutputs() {
        OutputEquation output = equation.getOutput();
    }

    private void drawTest() {
        OperatorElement element = new OperatorElement();
        element.setType(ElementsType.INPUT_SIGNALS);
        CellsCalculatedValue c = new CellsCalculatedValue(5, -3);

        element.setValue(c);
        OperatorElementWrapper wrapper = new OperatorElementWrapper(element);
        wrapper.setX(50);

        drawer.drawOperatorElement(wrapper);
        element.setType(ElementsType.F);
        wrapper = new OperatorElementWrapper(element);
        wrapper.setX(150);
        drawer.drawOperatorElement(wrapper);
    }


}
