package controller.GraphicUtils;

import controller.utils.CommonValues;
import javafx.beans.property.IntegerProperty;
import model.equationModel.ElementsType;
import model.equationModel.OperatorElement;

import java.util.List;

/**
 * Created by Volodymyr_Kychak on 7/28/14.
 */
public class OperatorElementWrapper {
    private OperatorElement operatorElement;
    private double x;
    private double y;

    private OperatorElementWrapper(){}

    public OperatorElementWrapper(OperatorElement element){
        this.operatorElement = element;
    }

    public ElementsType getType() {
        return operatorElement.getType();
    }

    public List<IntegerProperty> getInConnections() {
        return  operatorElement.getInConnections();
    }

    public List<IntegerProperty> getOutConnections() {
        return  operatorElement.getOutConnections();
    }

    public Object getValue() {
        return  operatorElement.getValue();
    }

    public double getHeight(){
        if(getElementGraphicType().equals(ElementGraphicType.BLOCK)){
            return CommonValues.LOGICAL_ELEMENT_HEIGHT;
        }else if (getElementGraphicType().equals(ElementGraphicType.SIGNAL)){
            return CommonValues.SIGNAL_ELEMENT_HEIGHT;
        }
        return 0 ;
    }
    public double getWidth(){
        if(getElementGraphicType().equals(ElementGraphicType.BLOCK)){
            return CommonValues.LOGICAL_ELEMENT_WIDTH;
        }else if (getElementGraphicType().equals(ElementGraphicType.SIGNAL)){
            return CommonValues.SIGNAL_ELEMENT_WIDTH;
        }
        return 0 ;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ElementGraphicType getElementGraphicType(){
        if(getType().equals(ElementsType.INPUT_SIGNALS)
                || getType().equals(ElementsType.OUTPUT_SIGNALS)
                || getType().equals(ElementsType.SUPPLEMENTARY_SIGNALS)){
            return ElementGraphicType.SIGNAL;
        }else{
            return ElementGraphicType.BLOCK;
        }
    }

    public static OperatorElementWrapper wrap(OperatorElement element){
        OperatorElementWrapper wrappedElement = new OperatorElementWrapper(element);
        return wrappedElement;
    }

}
