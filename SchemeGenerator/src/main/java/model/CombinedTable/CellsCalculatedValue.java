package model.combinedTable;

import javafx.beans.property.IntegerProperty;

import java.util.LinkedList;


public class CellsCalculatedValue {
    private CellValueType type;

    private int w0Quantity, w1Quantity = 0;


    public enum CellValueType{
        z, delta
    }

    public CellValueType getType() {
        return type;
    }

    public int getW0Quantity() {
        return w0Quantity;
    }

    public int getW1Quantity() {
        return w1Quantity;
    }

    public CellsCalculatedValue(CellValueType type, CombinedTableModelRow row){
        this.type = type;
        if(this.type == CellValueType.z){
            calculateZ(row.getWxList());
        }else if(this.type == CellValueType.delta){
            calculateDelta(row.getWyList(), row.getZ());
        }
    }

    private void calculateDelta(LinkedList<IntegerProperty> wyList, CellsCalculatedValue z) {
        for(IntegerProperty value: wyList){
            if(value.getValue() == 0){
                ++w0Quantity;
            }else if(value.getValue() == 1){
                ++w1Quantity;
            }
        }
        w0Quantity = z.getW0Quantity() - w0Quantity;
        w1Quantity = z.getW1Quantity() - w1Quantity;
    }

    void calculateZ (LinkedList<IntegerProperty> wxList){
        for(IntegerProperty value: wxList){
            if(value.getValue() == 0){
                ++w0Quantity;
            }else if(value.getValue() == 1){
                ++w1Quantity;
            }
        }
    }
}
