package model.equationModel;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public class OperatorEquation {
    LinkedList<OperatorElement> equationElements = new LinkedList<>();
    LinkedList<OperatorElement> preProcessingElements = new LinkedList<>();
    LinkedList<OperatorElement> multiplicationElements = new LinkedList<>();
    LinkedList<OperatorElement> preOutputElements = new LinkedList<>();


    public LinkedList<OperatorElement> getPreProcessingElements() {
        return preProcessingElements;
    }

    public LinkedList<OperatorElement> getMultiplicationElements() {
        return multiplicationElements;
    }

    public void setEquationElements(LinkedList<OperatorElement> equationElements) {
        this.equationElements = equationElements;
    }

    public void setPreProcessingElements(LinkedList<OperatorElement> equationElements) {
        this.preProcessingElements = equationElements;
    }

    public void setMultiplicationElements(LinkedList<OperatorElement> equationElements) {
        this.multiplicationElements = equationElements;
    }

    public LinkedList<OperatorElement> getPreOutputElements() {
        return preOutputElements;
    }

    public void setPreOutputElements(LinkedList<OperatorElement> preOutputElements) {
        this.preOutputElements = preOutputElements;
    }
}
