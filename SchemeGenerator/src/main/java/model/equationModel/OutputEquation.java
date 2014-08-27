package model.equationModel;

import javafx.beans.property.StringProperty;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class OutputEquation {

    LinkedList<OperatorElement> aElements = new LinkedList<>();
    LinkedList<OperatorElement> outputs = new LinkedList<>();

    public LinkedList<OperatorElement> getOutputs() {
        return outputs;
    }

    public void setOutputs(LinkedList<OperatorElement> outputs) {
        this.outputs = outputs;
    }

    public LinkedList<OperatorElement> getaElements() {
        return aElements;
    }

    public void setaElements(LinkedList<OperatorElement> aElements) {
        this.aElements = aElements;
    }
}
