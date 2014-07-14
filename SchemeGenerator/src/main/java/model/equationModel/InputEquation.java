package model.equationModel;

import com.sun.javafx.css.CalculatedValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class InputEquation {
    LinkedList<StringProperty> inputs = new LinkedList<>();
    LinkedList<CalculatedValue> supplementarySignals = new LinkedList<>();

    public LinkedList<StringProperty> getInputs() {
        return inputs;
    }

    public void setInputs(LinkedList<StringProperty> inputs) {
        this.inputs = inputs;
    }

    public LinkedList<CalculatedValue> getSupplementarySignals() {
        return supplementarySignals;
    }

    public void setSupplementarySignals(LinkedList<CalculatedValue> supplementarySignals) {
        this.supplementarySignals = supplementarySignals;
    }


}
