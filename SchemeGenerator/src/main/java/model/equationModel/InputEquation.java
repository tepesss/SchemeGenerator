package model.equationModel;

import com.sun.javafx.css.CalculatedValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class InputEquation {
    private LinkedList<OperatorElement> operatorElements;

    public void setInputEquation(LinkedList<OperatorElement> operatorElements) {
        this.operatorElements = operatorElements;
    }

    public LinkedList<OperatorElement> getOperatorElements() {
        return operatorElements;
    }
}
