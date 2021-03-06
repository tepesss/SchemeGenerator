package model.combinedTable;

import model.inputModel.InputModel;
import model.inputModel.InputTableRow;

import java.util.*;


public class CombinedTableModel {
    private List<CombinedTableModelRow> combinedTableModelRows = new LinkedList<CombinedTableModelRow>();
    private LinkedList<CellsCalculatedValue> deltaList = new LinkedList<>();

    public CombinedTableModel(InputModel inputModel, int yIndex) {
        List<CellsCalculatedValue> deltaList = new LinkedList<>();
        for (InputTableRow row : inputModel.getInputRows()) {
            CombinedTableModelRow combinedRow = new CombinedTableModelRow(row, yIndex, inputModel.isIndefinitelyDependent());
            deltaList.add(combinedRow.getDelta());
            combinedTableModelRows.add(combinedRow);
        }
        for (CellsCalculatedValue value : deltaList) {
            if (!containsValue(this.deltaList, value)) {
                this.deltaList.add(value);
            }
        }
        for (CombinedTableModelRow combinedRow : combinedTableModelRows) {
            combinedRow.setCListByDeltaList(this.deltaList);
        }
    }

    public LinkedList<CellsCalculatedValue> getDeltaList() {
        return deltaList;
    }

    public List<CombinedTableModelRow> getCombinedTableModelRows() {
        return combinedTableModelRows;
    }

    boolean containsValue(List<CellsCalculatedValue> list, CellsCalculatedValue value) {
        for (CellsCalculatedValue item : list) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
