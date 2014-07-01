package model.correspondenceTables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.combinedTable.CombinedTableModelRow;

import java.util.LinkedHashSet;

/**
 * Created by Admin on 28.06.2014.
 */
public class CorrespondenceTableRow {
    private final CombinedTableModelRow combinedRow;
    private final int cIndex;
    IntegerProperty cValue;

    public CorrespondenceTableRow(CombinedTableModelRow combinedRow, int cIndex) {
        this.combinedRow = combinedRow;
        this.cIndex = cIndex;
    }

    public void fillSignals(LinkedHashSet<SimpleStringProperty> supplementarySignals) {
        cValue = combinedRow.getcList().get(cIndex);
    }
}
