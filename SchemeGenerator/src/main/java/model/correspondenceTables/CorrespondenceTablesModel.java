package model.correspondenceTables;

import model.combinedTable.CombinedTableModel;

import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 3/6/14.
 */
public class CorrespondenceTablesModel {
    LinkedList<CorrespondenceTable> correspondenceTables = new LinkedList<CorrespondenceTable>();
    public CorrespondenceTablesModel(CombinedTableModel combinedTableModel){
        combinedTableModel.getDeltaSet();
        combinedTableModel.getCombinedTableModelRows();
    }
}
