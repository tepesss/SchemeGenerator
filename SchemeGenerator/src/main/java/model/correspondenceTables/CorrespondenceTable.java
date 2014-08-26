package model.correspondenceTables;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.combinedTable.CellsCalculatedValue;
import model.combinedTable.CombinedTableModel;
import model.combinedTable.CombinedTableModelRow;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Volodymyr_Kychak on 23.06.2014.
 */
public class CorrespondenceTable {
    private LinkedHashSet<IntegerProperty> supplementarySignals = new LinkedHashSet<>();
    private CellsCalculatedValue deltaValue;
    private LinkedList<CorrespondenceTableRow> correspondenceTableRows = new LinkedList<>();

    public CorrespondenceTable(CombinedTableModel combinedTableModel, CellsCalculatedValue deltaValue) {
        fillSupplementarySignals();
        this.deltaValue = deltaValue;
        createTable(combinedTableModel);
    }

    private void createTable(CombinedTableModel combinedTableModel) {
        int cIndex = 0;
        if(combinedTableModel.getDeltaList().contains(deltaValue)){
             cIndex = combinedTableModel.getDeltaList().indexOf(deltaValue);
        }
        List<CombinedTableModelRow> combinedRows = combinedTableModel.getCombinedTableModelRows();
        for (CombinedTableModelRow combinedRow : combinedRows){
            CorrespondenceTableRow row = new CorrespondenceTableRow(combinedRow, cIndex);
            row.fillSignals(supplementarySignals, deltaValue);
            correspondenceTableRows.add(row);
        }
    }

    private void fillSupplementarySignals() {
        supplementarySignals.add(new SimpleIntegerProperty(0));
        supplementarySignals.add(new SimpleIntegerProperty(1));
    }

    public LinkedHashSet<IntegerProperty> getSupplementarySignals() {
        return supplementarySignals;
    }

    public CellsCalculatedValue getDeltaValue() {
        return deltaValue;
    }

    public boolean isT(){
        boolean e1 = false;
        boolean e2 = false;
        for(CorrespondenceTableRow row: correspondenceTableRows){
            if(row.getSignalValues().getFirst().getValue()==1){
                e1 =true;
            };
            if(row.getSignalValues().getLast().getValue()==1){
                e2 =true;
            };
        }
        return e1 && e2;
    }
}
