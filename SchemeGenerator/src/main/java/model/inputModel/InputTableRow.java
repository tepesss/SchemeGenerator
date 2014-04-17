package model.inputModel;

import javafx.beans.property.IntegerProperty;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 1/6/14.
 */
public class InputTableRow {
    private LinkedList<IntegerProperty> wxList = new LinkedList<IntegerProperty>();
    private LinkedList<IntegerProperty> wyList = new LinkedList<IntegerProperty>();

    public InputTableRow() {
    }

    public InputTableRow(LinkedList<IntegerProperty> argWx, LinkedList<IntegerProperty> argWy) {
        this.wxList = argWx;
        this.wyList = argWy;
    }

    public LinkedList<IntegerProperty> getWyList() {
        return wyList;
    }

    public LinkedList<IntegerProperty> getWxList() {
        return wxList;
    }
}
