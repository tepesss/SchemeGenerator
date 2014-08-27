package controller.builder;

import javafx.beans.property.SimpleIntegerProperty;
import model.correspondenceTables.CorrespondenceTable;
import model.correspondenceTables.CorrespondenceTablesModel;
import model.equationModel.*;
import model.inputModel.InputModel;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/25/14.
 */
public class OperatorEquationBuilder extends BaseEquationBuilder {
    private InputModel inputModel;
    private InputEquation inputEquationModel;
    private OperatorEquation equation;
    private LinkedList<OperatorElement> preProcessingElements = new LinkedList<>();
    private LinkedList<OperatorElement> multiplicationElements = new LinkedList<>();
    private LinkedList<OperatorElement> preOutputElements = new LinkedList<>();
    private LinkedList<OperatorElement> filteringElements = new LinkedList<>();



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
        equation.setFilteringElements(filteringElements);
        return equation;
    }

    private void buildInputProcessing() {
        counter = inputEquationModel.getOperatorElements().size();
        LinkedList<OperatorElement> inputSignals = inputEquationModel.getSubListByType(ElementsType.INPUT_SIGNALS);
        //if (!inputModel.isIndefinitelyDependent()) {
            OperatorElement first = inputSignals.getFirst();
            OperatorElement last = inputSignals.getLast();
            for (OperatorElement element : inputSignals) {
                int index = inputSignals.lastIndexOf(element);
                if (index == 0) {
                    if (element == last) {
                        //TODO single input SIGNAL create logic connected to "HI"
                    } else {
                        if (inputModel.isIndefinitelyDependent()) {
                            OperatorElement tElement = new OperatorElement();
                            tElement.addAllInConnections(element.getOutConnections());
                            tElement.setType(ElementsType.T);
                            OperatorElement processingElement = new OperatorElement();
                            processingElement.addAllInConnections(element.getOutConnections());
                            //not an error
                            bindElements(tElement, processingElement);
                            bindElements(tElement, processingElement);
                            processingElement.setType(ElementsType.F);
                            preProcessingElements.add(tElement);
                            preProcessingElements.add(processingElement);
                        }else{
                            OperatorElement processingElement = new OperatorElement();
                            processingElement.addAllInConnections(element.getOutConnections());
                            processingElement.setType(ElementsType.F);
                            preProcessingElements.add(processingElement);
                        }

                    }
                } else if (element != last) {
                    if (index == 1) {
                        OperatorElement processingElement = preProcessingElements.getLast();
                        processingElement.addAllInConnections(element.getOutConnections());
                        addFilterH(processingElement, preProcessingElements);
                    } else {
                        //TODO functionality when element is normal
                    }
                } else if (element == last) {
                    if (index == 1) {
                        //TODO functionality when element is last and second
                        if (inputModel.isIndefinitelyDependent()) {
                            OperatorElement processingElement = preProcessingElements.getLast();

                            OperatorElement f = addFElement(processingElement, preProcessingElements);
                        }
                        OperatorElement processingElement = preProcessingElements.getLast();
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

        /*} else {
            //TODO should contain logic for Indefinitely dependent
        }*/
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
        OperatorElement aElement = new OperatorElement(ElementsType.A);
        OperatorElement f_RElement_0 = new OperatorElement(ElementsType.FILTER_R);
        bindElements(aElement, f_RElement_0);
        filteringElements.add(f_RElement_0);
        preOutputElements.add(aElement);
        for (OperatorElement element : multiplicationElements) {
            LinkedList<OperatorElement> supplOp = findConnectedElements(element, inputEquationModel.getSubListByType(ElementsType.SUPPLEMENTARY_SIGNALS));

            for (CorrespondenceTable table : tables) {
                {
                    if (!supplOp.isEmpty() && table.getDeltaValue().equals(supplOp.get(0).getValue())) {
                        OperatorElement tElement = new OperatorElement(ElementsType.T);
                        bindElements(element, tElement);
                        bindElements(tElement, aElement);
                        OperatorElement f_RElement_1 = new OperatorElement(ElementsType.FILTER_R);
                        bindElements(tElement, f_RElement_1);
                        filteringElements.add(f_RElement_1);
                        preOutputElements.add(tElement);
                    }else{
                        bindElements(element, aElement);
                    }
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





}
