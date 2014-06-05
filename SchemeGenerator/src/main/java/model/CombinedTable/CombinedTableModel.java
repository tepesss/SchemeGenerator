package model.combinedTable;

import model.inputModel.InputTableRow;

import java.util.LinkedList;
import java.util.List;


public class CombinedTableModel {
    private List<CombinedTableModelRow> combinedTableModelRows =  new LinkedList<CombinedTableModelRow>();
    public CombinedTableModel(List<InputTableRow> inputRows){
        for (InputTableRow row : inputRows){
            combinedTableModelRows.add(new CombinedTableModelRow(row));
        }
    }
}
