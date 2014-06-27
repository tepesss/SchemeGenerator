package model.combinedTable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.inputModel.InputTableRow;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;


public class CombinedTableModelRow {
    private LinkedList<IntegerProperty> wxList = new LinkedList<IntegerProperty>();
    private LinkedList<IntegerProperty> wyList = new LinkedList<IntegerProperty>();
    private LinkedList<IntegerProperty> cList = new LinkedList<IntegerProperty>();
    private CellsCalculatedValue z, delta;


    public CombinedTableModelRow (InputTableRow inputTableRow){
        wxList.addAll(inputTableRow.getWxList());
        wyList.addAll(inputTableRow.getWyList());
        z = new CellsCalculatedValue(CellsCalculatedValue.CellValueType.z, this);
        delta = new CellsCalculatedValue(CellsCalculatedValue.CellValueType.delta, this);
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

    public void setCListByDeltaList(LinkedList<CellsCalculatedValue> deltaList){

        for(CellsCalculatedValue deltaValue  : deltaList){
            if (deltaValue.equals(delta)){
                cList.add(new SimpleIntegerProperty(1));
            }else{
                cList.add(new SimpleIntegerProperty(0));
            }
        }
    }
}
