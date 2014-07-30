package controller.builder;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.combinedTable.CellsCalculatedValue;
import model.combinedTable.CombinedTableModel;
import model.combinedTable.CombinedTableModelRow;
import model.combinedTable.CombinedTablesModel;
import model.equationModel.ElementsType;
import model.equationModel.InputEquation;
import model.equationModel.OperatorElement;
import model.equationModel.OperatorEquationModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 7/25/14.
 */
public class InputEquationBuilder extends AbstractEquationBuilder {
    private LinkedList<OperatorElement> operatorList = new LinkedList<>();
    private int counter;

    public InputEquation build(OperatorEquationModel operatorEquationModel) {
        InputEquation inputEquation = new InputEquation();
        fillOperators();
        inputEquation.setInputEquation(operatorList);
        return inputEquation;
    }

    private void fillOperators() {
        CombinedTablesModel model = getModel(CombinedTablesModel.class);
        LinkedList<CombinedTableModel> combinedTableModelList = model.getCombinedTableModelList();
        for (CombinedTableModel e : combinedTableModelList) {
            processCombinedTableModel(e);
        }
    }

    private void processCombinedTableModel(CombinedTableModel model) {
        counter = 1;
        for (CombinedTableModelRow row : model.getCombinedTableModelRows()) {
            populateInputs(row.getWxList().size());
        }
        populateSupplementarySignals(model.getDeltaList());
    }

    private void populateInputs(int wxSize) {
        int i = wxSize;
        while (i > 0) {
            OperatorElement element = new OperatorElement();
            element.setType(ElementsType.INPUT_SIGNALS);
            element.setValue("Wx"+(--i));
            addToList(element);
        }
    }

    private void populateSupplementarySignals(LinkedList<CellsCalculatedValue> deltaList) {
        for (CellsCalculatedValue delta : deltaList) {
            OperatorElement element = new OperatorElement();
            element.setType(ElementsType.SUPPLEMENTARY_SIGNALS);
            element.setValue(delta);
            addToList(element);
        }
    }

    private void addToList(OperatorElement element) {
        boolean needToAdd = true;

        //// Contains function!!!!!!!!!!!!!!!!!!!!!!!!!!
        for (OperatorElement e : operatorList) {
            if (elementEquals(e, element)) {
                needToAdd = false;
            }
        }
        if (operatorList.isEmpty() || needToAdd) {
            addElement(element);
        }
    }

    private void addElement(OperatorElement element) {
        List<IntegerProperty> list = new ArrayList<IntegerProperty>();
        list.add(new SimpleIntegerProperty(counter));
        element.setOutConnections(list);
        operatorList.add(element);
        counter++;
    }

    private boolean elementEquals(OperatorElement e, OperatorElement element) {
        if (e.getType() == element.getType()) {
            if (e.getType() == ElementsType.SUPPLEMENTARY_SIGNALS) {
                return e.getValue().equals(element.getValue());
            } else if (e.getType() == ElementsType.INPUT_SIGNALS) {
                return e.getValue().equals(element.getValue());
            }
            return false;
        } else {
            return false;
        }
    }
}
