package model.equationModel;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class OperatorEquation {
    LinkedList<OperatorElement> equationElements = new LinkedList<>();

    public LinkedList<OperatorElement> getEquationElements() {
        return equationElements;
    }

    public void setEquationElements(LinkedList<OperatorElement> equationElements) {
        this.equationElements = equationElements;
    }
}
