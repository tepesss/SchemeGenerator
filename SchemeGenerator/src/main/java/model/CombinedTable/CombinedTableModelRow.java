package model.combinedTable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.inputModel.InputTableRow;

import java.util.LinkedList;


public class CombinedTableModelRow {
    private LinkedList<IntegerProperty> wxList = new LinkedList<IntegerProperty>();
    private IntegerProperty wY;
    private LinkedList<IntegerProperty> cList = new LinkedList<IntegerProperty>();
    private int yIndex = 0;
    private CellsCalculatedValue z, delta;


    public CombinedTableModelRow (InputTableRow inputTableRow, int yIndex){
        this.yIndex = yIndex;
        wxList.addAll(inputTableRow.getWxList());
        wY = inputTableRow.getWyList().get(yIndex);
        z = new CellsCalculatedValue(CellsCalculatedValue.CellValueType.z, this);
        delta = new CellsCalculatedValue(CellsCalculatedValue.CellValueType.delta, this);
    }

    public LinkedList<IntegerProperty> getWxList() {
        return wxList;
    }

    public IntegerProperty getwY() {
        return wY;
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
