package model.correspondenceTables;

import model.BaseModel;
import model.combinedTable.CellsCalculatedValue;
import model.combinedTable.CombinedTableModel;
import model.combinedTable.CombinedTablesModel;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * Created by Volodymyr_Kychak on 3/6/14.
 */
public class CorrespondenceTablesModel  extends BaseModel {
    LinkedList<CorrespondenceTable> correspondenceTables = new LinkedList<CorrespondenceTable>();

    public CorrespondenceTablesModel(CombinedTablesModel combinedTablesModel){
        for(CombinedTableModel combinedTableModel: combinedTablesModel.getCombinedTableModelList()){
            LinkedList<CellsCalculatedValue> deltaList = combinedTableModel.getDeltaList();
            //combinedTableModel.getCombinedTableModelRows();
            for(CellsCalculatedValue deltaValue : deltaList){
                correspondenceTables.add(new CorrespondenceTable(combinedTableModel, deltaValue));
            }
        }
    }
}
