package controller.builder;

import javafx.beans.property.StringProperty;
import model.equationModel.*;
import model.inputModel.InputModel;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 7/25/14.
 */
public class OutputEquationBuilder extends BaseEquationBuilder {
    private OperatorEquation operatorModel;
    private OutputEquation outputModel;
    LinkedList<OperatorElement> outputs = new LinkedList<>();
    LinkedList<OperatorElement> aElements = new LinkedList<>();

    public OutputEquation build(OperatorEquationModel operatorEquationModel) {
        operatorModel = operatorEquationModel.getOperator();
        outputModel = new OutputEquation();
        InputModel inputModel = getModel(InputModel.class);


        int q = inputModel.getWyQuantityVal();
        for (int i = 0; i < q; i++) {
            OperatorElement aElement = new OperatorElement();
            aElement.setType(ElementsType.A);
            aElements.add(aElement);
        }
        bindaElementsToFilters();
        bindOutputsToaElements();
        outputModel.setaElements(aElements);
        outputModel.setOutputs(outputs);
        return outputModel;
    }

    private void bindOutputsToaElements() {
        int i = 0;
        for(OperatorElement element : aElements){
            OperatorElement outputSignal = new OperatorElement();
            outputSignal.setType(ElementsType.OUTPUT_SIGNALS);
            outputSignal.setValue("Wy"+(i++));
            bindElements(element, outputSignal);
            outputs.add(outputSignal);
        }
    }

    private void bindaElementsToFilters() {
        LinkedList<OperatorElement> filteringElements = operatorModel.getFilteringElements();
        int i = 0;
        for(OperatorElement element : aElements){
            bindElements(filteringElements.get(i), element);
            bindElements(filteringElements.get(++i), element);
        }
    }


}
