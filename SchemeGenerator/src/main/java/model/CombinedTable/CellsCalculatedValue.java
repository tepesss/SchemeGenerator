package model.combinedTable;

import javafx.beans.property.IntegerProperty;
import model.inputModel.InputTableRow;

import java.util.LinkedList;


public class CellsCalculatedValue {
    private CellValueType type;

    private int w0Quantity, w1Quantity = 0;

    public CellsCalculatedValue(CellValueType z, CombinedTableModelRow row, boolean isIndefinitelyDependent) {
        this.type = CellValueType.z;
        if (this.type == CellValueType.z) {
            calculateZ(row.getWxList());
            if ( row.getWxList().get(0).getValue() == 0) {
                ++w0Quantity;
            } else if ( row.getWxList().get(0).getValue() == 1) {
                ++w1Quantity;
            }
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

    void calculateZ(LinkedList<IntegerProperty> wxList) {
        for (IntegerProperty value : wxList) {
            if (value.getValue() == 0) {
                ++w0Quantity;
            } else if (value.getValue() == 1) {
                ++w1Quantity;
            }
        }
    }

    private void calculateDelta(IntegerProperty value, CellsCalculatedValue z) {
        if (value.getValue() == 0) {
            ++w0Quantity;
        } else if (value.getValue() == 1) {
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
}
