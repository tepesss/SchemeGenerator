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
    OperatorElement operatorElement;
    double x;
    double y;

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

    private ElementGraphicType getElementGraphicType(){
        if(getType().equals(ElementsType.INPUT_SIGNALS)
                || getType().equals(ElementsType.OUTPUT_SIGNALS)
                || getType().equals(ElementsType.SUPPLEMENTARY_SIGNALS)){
            return ElementGraphicType.SIGNAL;
        }else{
            return ElementGraphicType.BLOCK;
        }
    }

    private enum ElementGraphicType {
        BLOCK, SIGNAL;
    }
}
