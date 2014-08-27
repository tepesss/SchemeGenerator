package controller.builder;

import controller.GraphicUtils.ConnectionsDrawer;
import controller.GraphicUtils.ImageDrawer;
import controller.GraphicUtils.OperatorElementWrapper;
import controller.utils.CommonValues;
import javafx.beans.property.IntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.AppContext;
import model.OutputModel;
import model.combinedTable.CellsCalculatedValue;
import model.equationModel.*;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class SchemeImageBuilder {
    public static final int X_START = 60;
    public static final int Y_START = 20;
    private OutputModel model;
    private OperatorEquationModel equation;
    private Canvas canvas;
    private GraphicsContext gc;
    private ImageDrawer drawer;
    private ConnectionsDrawer connector;
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
        connector = new ConnectionsDrawer(gc);
    }

    private void drawEquation() {
        drawInputs();
        double xPos = drawOperators();
        drawOutputs(xPos);
        drawConnections();
    }

    private void drawInputs() {
        InputEquation input = equation.getInput();
        LinkedList<OperatorElementWrapper> inputsList = wrapElements(input.getOperatorElements());
        double x = X_START;
        double y = Y_START;
        for (OperatorElementWrapper elementWrapper: inputsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            y+=50;
        }
    }

    private LinkedList<OperatorElementWrapper> wrapElements(LinkedList<OperatorElement> operatorList){
        LinkedList<OperatorElementWrapper> list = new LinkedList<>();
        for(OperatorElement element : operatorList){
            list.add(OperatorElementWrapper.wrap(element));
        }
        return list;
    }

    private double drawOperators() {
        double xPos = drawPreProcessingOperators();
        xPos = drawMultiplicationOperators(xPos + CommonValues.LOGICAL_ELEMENT_WIDTH);
        xPos = drawPreOutputOperators(xPos + CommonValues.LOGICAL_ELEMENT_WIDTH);
        return drawFilteringOperators(xPos + CommonValues.LOGICAL_ELEMENT_WIDTH);
    }

    private double drawPreProcessingOperators(){
        double x = X_START+CommonValues.LOGICAL_ELEMENT_WIDTH*2;
        double y = Y_START;
        OperatorEquation operator = equation.getOperator();
        LinkedList<OperatorElementWrapper> elementsList = wrapElements(operator.getPreProcessingElements());
        for (OperatorElementWrapper elementWrapper: elementsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            x+=CommonValues.LOGICAL_ELEMENT_WIDTH*2;
        }
        return elementsList.getLast().getX();
    }

    private double drawMultiplicationOperators(double lastElementXPosition){
        double x = lastElementXPosition+CommonValues.LOGICAL_ELEMENT_WIDTH*2;
        double y = Y_START;
        OperatorEquation operator = equation.getOperator();
        LinkedList<OperatorElementWrapper> elementsList = wrapElements(operator.getMultiplicationElements());
        for (OperatorElementWrapper elementWrapper: elementsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            y+=50;
        }
        return elementsList.getLast().getX();
    }

    private double drawPreOutputOperators(double lastElementXPosition){
        double x = lastElementXPosition+CommonValues.LOGICAL_ELEMENT_WIDTH*3;
        double y = Y_START;
        OperatorEquation operator = equation.getOperator();
        LinkedList<OperatorElementWrapper> elementsList = wrapElements(operator.getPreOutputElements());
        for (OperatorElementWrapper elementWrapper: elementsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            y+=50;
        }
        return elementsList.getLast().getX();
    }

    private double drawFilteringOperators(double lastElementXPosition){
        double x = lastElementXPosition+CommonValues.LOGICAL_ELEMENT_WIDTH*3;
        double y = Y_START;
        OperatorEquation operator = equation.getOperator();
        LinkedList<OperatorElementWrapper> elementsList = wrapElements(operator.getFilteringElements());
        for (OperatorElementWrapper elementWrapper: elementsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            y+=50;
        }
        return elementsList.getLast().getX();
    }

    private double drawOutputs(double lastElementXPosition) {
        double xPos = drawaElements(lastElementXPosition);
        return drawOutputSignals(xPos);
    }

    private double drawaElements(double lastElementXPosition){
        OutputEquation output = equation.getOutput();
        double x = lastElementXPosition+CommonValues.LOGICAL_ELEMENT_WIDTH*3;
        double y = Y_START;
        LinkedList<OperatorElementWrapper> elementsList = wrapElements(output.getaElements());
        for (OperatorElementWrapper elementWrapper: elementsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            y+=50;
        }
        return elementsList.getLast().getX();
    }

    private double drawOutputSignals(double lastElementXPosition){
        OutputEquation output = equation.getOutput();
        double x = lastElementXPosition+CommonValues.LOGICAL_ELEMENT_WIDTH*1.75;
        double y = Y_START;
        LinkedList<OperatorElementWrapper> elementsList = wrapElements(output.getOutputs());
        for (OperatorElementWrapper elementWrapper: elementsList){
            elementWrapper.setX(x);
            elementWrapper.setY(y);
            drawer.drawOperatorElement(elementWrapper);
            drawnElements.add(elementWrapper);
            y+=50;
        }
        return elementsList.getLast().getX();
    }


    private void drawConnections() {
        for(OperatorElementWrapper element:drawnElements){
            for(IntegerProperty outConnection : element.getOutConnections()){
                OperatorElementWrapper elementWithConnection = getElementByInConnection(outConnection);
                if(elementWithConnection != null){
                    connector.connect(element, getElementByInConnection(outConnection));
                }
            }
        }

    }

    private OperatorElementWrapper getElementByInConnection(IntegerProperty connection) {
        for(OperatorElementWrapper element:drawnElements){
            for(IntegerProperty inConnection : element.getInConnections()){
                if(inConnection.getValue() == connection.getValue()){
                    return element;
                }
            }
        }
        return null;
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
