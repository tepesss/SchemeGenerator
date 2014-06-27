package model.correspondenceTables;

import model.combinedTable.CellsCalculatedValue;
import model.combinedTable.CombinedTableModel;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 3/6/14.
 */
public class CorrespondenceTablesModel {
    LinkedList<CorrespondenceTable> correspondenceTables = new LinkedList<CorrespondenceTable>();

    public CorrespondenceTablesModel(CombinedTableModel combinedTableModel){
        LinkedList<CellsCalculatedValue> deltaList = combinedTableModel.getDeltaList();
        //combinedTableModel.getCombinedTableModelRows();
        for(CellsCalculatedValue deltaValue : deltaList){
            correspondenceTables.add(new CorrespondenceTable(combinedTableModel, deltaValue));
        }

    }
}
