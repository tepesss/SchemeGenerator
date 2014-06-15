package model.combinedTable;

import model.inputModel.InputTableRow;

import java.util.*;


public class CombinedTableModel {
    private List<CombinedTableModelRow> combinedTableModelRows = new LinkedList<CombinedTableModelRow>();

    public CombinedTableModel(List<InputTableRow> inputRows) {
        List<CellsCalculatedValue> deltaList = new LinkedList<>();
        for (InputTableRow row : inputRows) {
            CombinedTableModelRow combinedRow = new CombinedTableModelRow(row);
            deltaList.add(combinedRow.getDelta());
            combinedTableModelRows.add(combinedRow);
        }
        LinkedHashSet<CellsCalculatedValue> deltaSet = new LinkedHashSet<>();
        for (CellsCalculatedValue value : deltaList) {
            if (!containsValue(deltaSet, value)) {
                deltaSet.add(value);
            }
        }
        for (CombinedTableModelRow combinedRow : combinedTableModelRows) {
            combinedRow.setCListByDeltaSet(deltaSet);
        }
        System.out.println(combinedTableModelRows);
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
