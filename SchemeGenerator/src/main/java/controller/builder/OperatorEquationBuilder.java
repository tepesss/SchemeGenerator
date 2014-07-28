package controller.builder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.equationModel.*;
import model.inputModel.InputModel;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/25/14.
 */
public class OperatorEquationBuilder extends AbstractEquationBuilder {
    private InputModel inputModel;
    private InputEquation inputEquationModel;
    private OperatorEquation equation;
    private LinkedList<OperatorElement> operatorsList = new LinkedList<>();
    int counter = -999;


    public OperatorEquation build(OperatorEquationModel operatorEquationModel) {
        equation = new OperatorEquation();
        inputEquationModel = operatorEquationModel.getInput();
        inputModel = getModel(InputModel.class);
        buildInputProcessing();
        buildInputAndSupplementaryConnections();
        equation.setEquationElements(operatorsList);
        return equation;
    }

    private void buildInputProcessing() {
        counter = inputEquationModel.getOperatorElements().size();
        LinkedList<OperatorElement> inputSignals = inputEquationModel.getSubListByType(ElementsType.INPUT_SIGNALS);
        if (!inputModel.isIndefinitelyDependent()) {
            OperatorElement first = inputSignals.getFirst();
            OperatorElement last = inputSignals.getLast();
            for (OperatorElement element : inputSignals) {
                int index = inputSignals.lastIndexOf(element);
                if (index == 0) {
                    if (element == last) {
                        //TODO single input SIGNAL create logic connected to "HI"
                    } else {
                        OperatorElement processingElement = new OperatorElement();
                        processingElement.addAllInConnections(element.getOutConnections());
                        processingElement.setType(ElementsType.F);
                        operatorsList.add(processingElement);
                    }
                } else if (element != last) {
                    if (index == 1) {
                        OperatorElement processingElement = operatorsList.get(0);
                        processingElement.addAllInConnections(element.getOutConnections());
                        addFilterH(processingElement);
                    } else {
                        //TODO functionality when element is normal
                    }
                } else if (element == last) {
                    if (index == 1) {
                        //TODO functionality when element is last and second
                        OperatorElement processingElement = operatorsList.get(0);
                        processingElement.addAllInConnections(element.getOutConnections());
                        OperatorElement filterH = addFilterH(processingElement);

                    }
                        //TODO functionality when element is last
                        OperatorElement processingElement = operatorsList.getLast();
                        OperatorElement tElement = addTElement(processingElement);

                }
            }

        } else {
            //TODO should contain logic for Indefinitely dependent
        }
    }


    private OperatorElement addFilterH(OperatorElement element) {
        OperatorElement filterH = createBindedElement(element);
        filterH.setType(ElementsType.FILTER_H);
        operatorsList.add(filterH);
        return filterH;
    }

    private OperatorElement addTElement(OperatorElement element){
        OperatorElement tElement = createBindedElement(element);
        tElement.setType(ElementsType.T);
        operatorsList.add(tElement);
        return tElement;
    }

    private OperatorElement createBindedElement(OperatorElement element){
        SimpleIntegerProperty connection = new SimpleIntegerProperty(++counter);
        element.addOutConnection(connection);
        OperatorElement resultElement = new OperatorElement();
        resultElement.addInConnection(connection);
        return resultElement;
    }



    private void buildInputAndSupplementaryConnections() {

    }


}
