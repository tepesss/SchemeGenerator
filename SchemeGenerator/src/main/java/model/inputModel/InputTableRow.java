package model.inputModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 1/6/14.
 */
public class InputTableRow {
    private LinkedList<IntegerProperty> wxBitSet = new LinkedList<IntegerProperty>();
    private LinkedList<IntegerProperty> wyBitSet = new LinkedList<IntegerProperty>();

    public InputTableRow() {
    }

    public InputTableRow(LinkedList<IntegerProperty> argWx, List<IntegerProperty> argWy) {
        this.wxBitSet = argWx;
        //this.wyBitSet = argWy;
    }

    public void setWyBitSet(LinkedList<IntegerProperty> wyBitSet) {
        this.wyBitSet = wyBitSet;
    }

    public void setWxBitSet(LinkedList<IntegerProperty> wxBitSet) {
        this.wxBitSet = wxBitSet;
    }

    public LinkedList<IntegerProperty> getWyBitSet() {
        return wyBitSet;
    }

    public LinkedList<IntegerProperty> getWxBitSet() {
        return wxBitSet;
    }
}
