package model.correspondenceTables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.combinedTable.CombinedTableModelRow;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Created by Admin on 28.06.2014.
 */
public class CorrespondenceTableRow {
    private final CombinedTableModelRow combinedRow;
    private LinkedList<IntegerProperty> signalValues = new LinkedList<>();
    private final int cIndex;
    IntegerProperty cValue;

    public CorrespondenceTableRow(CombinedTableModelRow combinedRow, int cIndex) {
        this.combinedRow = combinedRow;
        this.cIndex = cIndex;
    }

    public void fillSignals(LinkedHashSet<IntegerProperty> supplementarySignals) {
        cValue = combinedRow.getcList().get(cIndex);
        for(IntegerProperty signal : supplementarySignals){
            if(cValue.getValue()!=1){
                signalValues.add(new SimpleIntegerProperty(0));
            }else{
                calculateSignalValues(signal);
            }
        }
    }

    private void calculateSignalValues(IntegerProperty signal){

    }
}
