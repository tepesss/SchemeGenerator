package model.equationModel;

import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class OperatorElement {
    private LinkedList<LogicalElementsTypes> typedList = new LinkedList<>();
    private List<IntegerProperty> connections = new ArrayList<>();

    public LinkedList<LogicalElementsTypes> getTypedList() {
        return typedList;
    }

    public void setTypedList(LinkedList<LogicalElementsTypes> typedList) {
        this.typedList = typedList;
    }

    public List<IntegerProperty> getConnections() {
        return connections;
    }

    public void setConnections(List<IntegerProperty> connections) {
        this.connections = connections;
    }
}
