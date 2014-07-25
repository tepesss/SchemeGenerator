package model.combinedTable;

import model.IBaseModel;
import model.inputModel.InputModel;
import model.inputModel.InputTableRow;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 30.06.2014.
 */
public class CombinedTablesModel implements IBaseModel{
    LinkedList<CombinedTableModel> combinedTableModelList = new LinkedList<>();

    public CombinedTablesModel(InputModel inputModel) {
        for (int i = 0; i < inputModel.getInputRows().get(0).getWyList().size(); i++) {
            combinedTableModelList.add(new CombinedTableModel(inputModel, i));
        }
    }

    public LinkedList<CombinedTableModel> getCombinedTableModelList() {
        return combinedTableModelList;
    }


}
