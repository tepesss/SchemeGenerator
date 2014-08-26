package controller.builder;

import controller.GraphicUtils.OperatorElementWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import model.correspondenceTables.CorrespondenceTable;
import model.correspondenceTables.CorrespondenceTablesModel;
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
    private LinkedList<OperatorElement> preProcessingElements = new LinkedList<>();
    private LinkedList<OperatorElement> multiplicationElements = new LinkedList<>();
    private LinkedList<OperatorElement> preOutputElements = new LinkedList<>();
    int counter = -999;


    public OperatorEquation build(OperatorEquationModel operatorEquationModel) {
        equation = new OperatorEquation();
        inputEquationModel = operatorEquationModel.getInput();
        inputModel = getModel(InputModel.class);
        buildInputProcessing();
        buildSupplSignalsProcessing();
        buildPreOutputProcessing();
        equation.setPreProcessingElements(preProcessingElements);
        equation.setMultiplicationElements(multiplicationElements);
        equation.setPreOutputElements(preOutputElements);
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
                        preProcessingElements.add(processingElement);
                    }
                } else if (element != last) {
                    if (index == 1) {
                        OperatorElement processingElement = preProcessingElements.get(0);
                        processingElement.addAllInConnections(element.getOutConnections());
                        addFilterH(processingElement, preProcessingElements);
                    } else {
                        //TODO functionality when element is normal
                    }
                } else if (element == last) {
                    if (index == 1) {
                        //TODO functionality when element is last and second
                        OperatorElement processingElement = preProcessingElements.get(0);
                        processingElement.addAllInConnections(element.getOutConnections());
                        OperatorElement filterH = addFilterH(processingElement, preProcessingElements);

                    } else {
                        OperatorElement processingElement = new OperatorElement();
                        processingElement.setType(ElementsType.F);
                        bindElements(preProcessingElements.getLast(), processingElement);
                        preProcessingElements.add(processingElement);
                        OperatorElement filterH = addFilterH(processingElement, preProcessingElements);
                        processingElement.addAllInConnections(element.getOutConnections());
                    }
                    //TODO functionality when element is last
                    OperatorElement processingElement = preProcessingElements.getLast();
                    OperatorElement tElement = addTElement(processingElement, preProcessingElements);

                }
            }

        } else {
            //TODO should contain logic for Indefinitely dependent
        }
    }

    private void buildSupplSignalsProcessing() {
        LinkedList<OperatorElement> supplSignals = inputEquationModel.getSubListByType(ElementsType.SUPPLEMENTARY_SIGNALS);
        OperatorElement tElement = preProcessingElements.getLast();
        for (OperatorElement e : supplSignals) {
            OperatorElement f = addFElement(tElement, multiplicationElements);
            bindElements(e, f);
        }
    }

    private void buildPreOutputProcessing() {
        CorrespondenceTablesModel correspondenceTablesModel = getModel(CorrespondenceTablesModel.class);
        LinkedList<CorrespondenceTable> tables = correspondenceTablesModel.getTablesWithT();
        for (OperatorElement element : multiplicationElements) {
            LinkedList<OperatorElement> supplOp = findConnectedElements(element, inputEquationModel.getSubListByType(ElementsType.SUPPLEMENTARY_SIGNALS));

            for (CorrespondenceTable table : tables) {
                {
                    if (!supplOp.isEmpty() && table.getDeltaValue().equals(supplOp.get(0).getValue())) {
                        OperatorElement tElement = new OperatorElement(ElementsType.T);
                        bindElements(element, tElement);
                        preOutputElements.add(tElement);
                    }
                    // table.getDeltaValue().equals()
                }
            }
        }
    }

    private OperatorElement addFilterH(OperatorElement element, LinkedList<OperatorElement> operatorsList) {
        OperatorElement filterH = createBindedElement(element);
        filterH.setType(ElementsType.FILTER_H);
        operatorsList.add(filterH);
        return filterH;
    }

    private OperatorElement addTElement(OperatorElement element, LinkedList<OperatorElement> operatorsList) {
        OperatorElement tElement = createBindedElement(element);
        tElement.setType(ElementsType.T);
        operatorsList.add(tElement);
        return tElement;
    }

    private OperatorElement addFElement(OperatorElement element, LinkedList<OperatorElement> operatorsList) {
        OperatorElement fElement = createBindedElement(element);
        fElement.setType(ElementsType.F);
        operatorsList.add(fElement);
        return fElement;
    }

    private OperatorElement createBindedElement(OperatorElement element) {
        SimpleIntegerProperty connection = new SimpleIntegerProperty(++counter);
        element.addOutConnection(connection);
        OperatorElement resultElement = new OperatorElement();
        resultElement.addInConnection(connection);
        return resultElement;
    }

    private void bindElements(OperatorElement out, OperatorElement in) {
        SimpleIntegerProperty connection = new SimpleIntegerProperty(++counter);
        out.addOutConnection(connection);
        in.addInConnection(connection);
    }

}
