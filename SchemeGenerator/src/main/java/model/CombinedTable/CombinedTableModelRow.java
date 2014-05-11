package model.CombinedTable;

import javafx.beans.property.IntegerProperty;
import model.inputModel.InputTableRow;

import java.util.LinkedList;


public class CombinedTableModelRow {
    private LinkedList<IntegerProperty> wxList, wyList, cList = new LinkedList<IntegerProperty>();
    private CellsCalculatedValue z, delta;


    public CombinedTableModelRow (InputTableRow inputTableRow){
        wxList.addAll(inputTableRow.getWxList());
        wyList.addAll(inputTableRow.getWyList());
        z = new CellsCalculatedValue(CellsCalculatedValue.CellValueType.z, this);
        delta = new CellsCalculatedValue(CellsCalculatedValue.CellValueType.delta, this);
        cList = calculateCList();
    }

    private LinkedList<IntegerProperty> calculateCList() {
        return null;
    }

    public LinkedList<IntegerProperty> getWxList() {
        return wxList;
    }

    public LinkedList<IntegerProperty> getWyList() {
        return wyList;
    }

    public LinkedList<IntegerProperty> getcList() {
        return cList;
    }

    public CellsCalculatedValue getZ() {
        return z;
    }

    public CellsCalculatedValue getDelta() {
        return delta;
    }
}
