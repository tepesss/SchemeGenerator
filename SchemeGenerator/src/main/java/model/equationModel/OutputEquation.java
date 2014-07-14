package model.equationModel;

import javafx.beans.property.StringProperty;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class OutputEquation {
    LinkedList<StringProperty> outputs = new LinkedList<>();

    public LinkedList<StringProperty> getOutputs() {
        return outputs;
    }

    public void setOutputs(LinkedList<StringProperty> outputs) {
        this.outputs = outputs;
    }
}
