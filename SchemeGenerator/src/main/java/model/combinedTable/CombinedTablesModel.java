package model.combinedTable;

import model.inputModel.InputModel;
import model.inputModel.InputTableRow;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 30.06.2014.
 */
public class CombinedTablesModel {
    public CombinedTablesModel(InputModel inputModel) {
        int i = inputModel.getInputRows().get(0).getWyList().size();
        while(i>0){
            --i;
            combinedTableModelList.add(new CombinedTableModel(inputModel, i));
        }
    }

    public LinkedList<CombinedTableModel> getCombinedTableModelList() {
        return combinedTableModelList;
    }

    LinkedList<CombinedTableModel> combinedTableModelList = new LinkedList<>();
    public CombinedTablesModel(List<InputTableRow> inputRows) {

        int i = inputRows.get(0).getWyList().size();
        while(i>0){
            --i;
            combinedTableModelList.add(new CombinedTableModel(inputRows, i));
        }
    }
}
