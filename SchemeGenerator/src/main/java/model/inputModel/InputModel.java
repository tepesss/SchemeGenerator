package model.inputModel;

import com.sun.javafx.css.CalculatedValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.BaseModel;
import model.combinedTable.CellsCalculatedValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputModel extends BaseModel implements Serializable {

    private ObservableList<InputTableRow> inputRows = FXCollections.observableArrayList();
    private transient List<TableColumn> tableColumnsList = new ArrayList<TableColumn>();
    private IntegerProperty wxQuantity = new SimpleIntegerProperty(2);
    private IntegerProperty wyQuantity = new SimpleIntegerProperty(2);
    private Callback<TableColumn, TableCell> cellFactory = new EditableCellCallback();
    private boolean isIndefinitelyDependent = false;




    public void checkForDependency() {
        isIndefinitelyDependent = false;
        TreeMap<CellsCalculatedValue, Integer> map = new TreeMap();
        for(InputTableRow row :inputRows){
            row.calculateZ0Value();

            for(IntegerProperty y: row.getWyList()){
                CellsCalculatedValue zValue = row.getZ0();
                int yValue = y.getValue();
                if(map.containsKey(zValue)){
                    if(map.get(zValue)!= yValue){
                        isIndefinitelyDependent = true;
                    }
                }else{
                    map.put(zValue, yValue);
                }
            }
        }
    }

    public enum TableColumnsTypes {
        WX("Wx"), WY("Wy");

        private final String name;

        private TableColumnsTypes(String s) {
            name = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : name.equals(otherName);
        }

        public String toString() {
            return name;
        }
    }

    public List<TableColumn> getTableColumnsList() {
        return tableColumnsList;
    }

    public TableColumn[] getColumnsAsArray() {
        TableColumn[] array = new TableColumn[tableColumnsList.size()];
        tableColumnsList.toArray(array);
        return array;
    }

    public int getWxQuantityVal() {
        return wxQuantity.get();
    }

    public IntegerProperty getWxQuantity() {
        return wxQuantity;
    }

    public int getWyQuantityVal() {
        return wyQuantity.get();
    }

    public IntegerProperty getWyQuantity() {
        return wyQuantity;
    }

    public ObservableList<InputTableRow> getInputRows() {
        return inputRows;
    }

    public void createColumns() {
        addColumns(inputRows.get(0), cellFactory, TableColumnsTypes.WX);
        addColumns(inputRows.get(0), cellFactory, TableColumnsTypes.WY);
    }

    private void addColumns(InputTableRow row, Callback<TableColumn, TableCell> cellFactory, final TableColumnsTypes colType) {
        int size = colType.equals(TableColumnsTypes.WX) ? row.getWxList().size() : row.getWyList().size();
        for (Integer i = 0; i < size; i++) {
            tableColumnsList.add(createColumn(i, colType));
        }
    }

    public TableColumn createColumn(int i, final TableColumnsTypes colType) {
        TableColumn col = new TableColumn(colType.name + i);
        final int j = i;
        col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, Integer>, IntegerProperty>) (param) -> {
            InputTableRow someRow = (InputTableRow) param.getValue();
            if (colType.equals(TableColumnsTypes.WX)) {
                return someRow.getWxList().size() > j ? someRow.getWxList().get(j) : null;
            } else {
                return someRow.getWyList().size() > j ? someRow.getWyList().get(j) : null;
            }
        });
        col.setCellFactory(cellFactory);
        col.setPrefWidth(30.0);
        return col;
    }

    public void updateRowQuantity(int oldVal, int newVal) {
        if (oldVal < newVal) {
            int delta = newVal * newVal - oldVal * oldVal;
            if (oldVal == 1) {
                --delta;
            }
            for (int i = 0; i < delta; i++) {
                inputRows.add(getEmptyRow(newVal, inputRows.get(0).getWyList().size()));
            }
        } else {
            int delta = oldVal * oldVal - newVal * newVal;
            if (inputRows.size() - delta == 1) {
                --delta;
            }
            inputRows.remove(inputRows.size() - delta, inputRows.size());
        }
    }

    public InputTableRow getEmptyRow(int x, int y) {
        InputTableRow row = new InputTableRow();
        for (int i = 0; i < x; i++)
            row.getWxList().add(new SimpleIntegerProperty(0));
        for (int i = 0; i < y; i++)
            row.getWyList().add(new SimpleIntegerProperty(0));
        return row;
    }
    public boolean isIndefinitelyDependent() {
        return isIndefinitelyDependent;
    }
}
