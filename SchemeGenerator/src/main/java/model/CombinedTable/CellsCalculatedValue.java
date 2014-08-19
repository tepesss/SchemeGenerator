package model.combinedTable;

import javafx.beans.property.IntegerProperty;
import model.inputModel.InputTableRow;

import java.util.LinkedList;


public class CellsCalculatedValue implements Comparable {
    private CellValueType type;

    private int w0Quantity, w1Quantity = 0;

    public CellsCalculatedValue(CellValueType z, CombinedTableModelRow row, boolean isIndefinitelyDependent) {
        this.type = CellValueType.z;
        if (this.type == CellValueType.z) {
            calculateZ(row.getWxList());
            if (isIndefinitelyDependent) {
                if (row.getWxList().get(0).getValue() == 0) {
                    ++w0Quantity;
                } else if (row.getWxList().get(0).getValue() == 1) {
                    ++w1Quantity;
                }
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        if (this.equals(o)) {
            return 0;
        } else {
            return -1;
        }
    }


    public enum CellValueType {
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

    public CellsCalculatedValue(CellValueType type, CombinedTableModelRow row) {
        this.type = type;
        if (this.type == CellValueType.z) {
            calculateZ(row.getWxList());
        } else if (this.type == CellValueType.delta) {
            calculateDelta(row.getwY(), row.getZ());
        }
    }

    public CellsCalculatedValue(InputTableRow row) {
        this.type = CellValueType.z;
        if (this.type == CellValueType.z) {
            calculateZ(row.getWxList());
        }
    }
    public CellsCalculatedValue(int w0, int w1) {
        this.w1Quantity = w1;
        this.w0Quantity = w0;
    }

    void calculateZ(LinkedList<IntegerProperty> wxList) {
        for (IntegerProperty value : wxList) {
            if (value.getValue() == 0) {
                ++w0Quantity;
            } else if (value.getValue() == 1) {
                ++w1Quantity;
            }
        }
    }

    private void calculateDelta(IntegerProperty wY, CellsCalculatedValue z) {
        if (wY.getValue() == 0) {
            ++w0Quantity;
        } else if (wY.getValue() == 1) {
            ++w1Quantity;
        }
        w0Quantity = z.getW0Quantity() - w0Quantity;
        w1Quantity = z.getW1Quantity() - w1Quantity;
    }


    @Override
    public boolean equals(Object obj) {
        CellsCalculatedValue compared = (CellsCalculatedValue) obj;
        if (compared.getType() == (this.getType()) &&
                compared.getW0Quantity() == this.getW0Quantity() &&
                compared.getW1Quantity() == this.getW1Quantity()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(w0Quantity!=0){
            result.append(w0Quantity+"W0");
        }
        if(w1Quantity!=0){
            if(w0Quantity!=0){
                result.append(" + "+w1Quantity+"W1");
            }else {
                result.append(w1Quantity+"W1");
            }
        }
        return result.toString();
    }
}
