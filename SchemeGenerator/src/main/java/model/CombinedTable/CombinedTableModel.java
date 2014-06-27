package model.combinedTable;

import model.inputModel.InputTableRow;

import java.util.*;


public class CombinedTableModel {
    private List<CombinedTableModelRow> combinedTableModelRows = new LinkedList<CombinedTableModelRow>();
    private LinkedHashSet<CellsCalculatedValue> deltaSet = new LinkedHashSet<>();
    public CombinedTableModel(List<InputTableRow> inputRows) {
        List<CellsCalculatedValue> deltaList = new LinkedList<>();
        for (InputTableRow row : inputRows) {
            CombinedTableModelRow combinedRow = new CombinedTableModelRow(row);
            deltaList.add(combinedRow.getDelta());
            combinedTableModelRows.add(combinedRow);
        }
        for (CellsCalculatedValue value : deltaList) {
            if (!containsValue(deltaSet, value)) {
                deltaSet.add(value);
            }
        }
        for (CombinedTableModelRow combinedRow : combinedTableModelRows) {
            combinedRow.setCListByDeltaSet(deltaSet);
        }
    }

    public LinkedHashSet<CellsCalculatedValue> getDeltaSet() {
        return deltaSet;
    }

    public List<CombinedTableModelRow> getCombinedTableModelRows() {
        return combinedTableModelRows;
    }

    boolean containsValue(Set<CellsCalculatedValue> set, CellsCalculatedValue value) {
        for (CellsCalculatedValue item : set) {
            if (item.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
