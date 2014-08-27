package controller.builder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.AppContext;
import model.IBaseModel;
import model.equationModel.OperatorElement;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Volodymyr_Kychak on 7/25/14.
 */
public class BaseEquationBuilder {
    public static int counter = -999;
    protected  <T> T getModel(Class<T> clazz) {
        Map<String, IBaseModel>  map = AppContext.getInstance().getModels();
        if (map.containsKey(clazz.getName())){
            return (T) AppContext.getInstance().getModels().get(clazz.getName());
        }
        return null;
    }

    protected LinkedList<OperatorElement> findConnectedElements(OperatorElement element, List<OperatorElement> list){
        LinkedList<OperatorElement> connectedToElementList =  new LinkedList<>();
        for(IntegerProperty connection : element.getInConnections()){
            for(OperatorElement e: list){
                if( e.getOutConnections().contains(connection) ||
                        e.getInConnections().contains(connection)){
                    connectedToElementList.add(e);
                }
            }
        }
        for(IntegerProperty connection : element.getOutConnections()){
            for(OperatorElement e: list){
                if( e.getOutConnections().contains(connection) ||
                        e.getInConnections().contains(connection)){
                    connectedToElementList.add(e);
                }
            }
        }
        return connectedToElementList;
    }

    protected void bindElements(OperatorElement out, OperatorElement in) {
        SimpleIntegerProperty connection = new SimpleIntegerProperty(++counter);
        out.addOutConnection(connection);
        in.addInConnection(connection);
    }
    protected OperatorElement createBindedElement(OperatorElement element) {
        SimpleIntegerProperty connection = new SimpleIntegerProperty(++counter);
        element.addOutConnection(connection);
        OperatorElement resultElement = new OperatorElement();
        resultElement.addInConnection(connection);
        return resultElement;
    }

}
