package model.equationModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class OperatorElement {
    private ElementsType type;
    private List<IntegerProperty> inConnections = new ArrayList<>();
    private List<IntegerProperty> outConnections = new ArrayList<>();
    private Object value;

    public ElementsType getType() {
        return type;
    }

    public void setType(ElementsType type) {
        this.type = type;
    }

    public List<IntegerProperty> getInConnections() {
        return inConnections;
    }

    public void setInConnections(List<IntegerProperty> inConnections) {
        this.inConnections = inConnections;
    }

    public List<IntegerProperty> getOutConnections() {
        return outConnections;
    }

    public void setOutConnections(List<IntegerProperty> outConnections) {
        this.outConnections = outConnections;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
