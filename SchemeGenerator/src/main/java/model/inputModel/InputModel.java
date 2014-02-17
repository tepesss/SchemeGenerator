package model.inputModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.BaseModel;
import model.EditableCell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputModel extends BaseModel implements Serializable{

    //    private static InputModel instance;
    private ObservableList<InputTableRow> inputRows = FXCollections.observableArrayList();
    List<TableColumn> tableColumnsList = new ArrayList<TableColumn>();
    private IntegerProperty wxQuantity = new SimpleIntegerProperty(2);
    private IntegerProperty wyQuantity = new SimpleIntegerProperty(2);

    public List<TableColumn> getTableColumnsList() {
        return tableColumnsList;
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


    public ObservableList<InputTableRow> getInputRows() {
        return inputRows;
    }

    private Callback<TableColumn, TableCell> cellFactory =
            new Callback<TableColumn, TableCell>() {
                public TableCell call(TableColumn p) {
                    return new EditableCell();
                }
            };

    public TableColumn[] getColumns() {

        Callback<TableColumn.CellDataFeatures<ObservableList, Integer>, SimpleIntegerProperty> cellValueFactory = null;
        createColumns(inputRows.get(0), cellFactory, TableColumnsTypes.WX);
        createColumns(inputRows.get(0), cellFactory, TableColumnsTypes.WY);
        TableColumn[] array = new TableColumn[tableColumnsList.size()];
        tableColumnsList.toArray(array);
        return array;
    }

    private void createColumns(InputTableRow row, Callback<TableColumn, TableCell> cellFactory, final TableColumnsTypes colType) {
        int size = colType.equals(TableColumnsTypes.WX) ? row.getWxBitSet().size() : row.getWyBitSet().size();
        for (Integer i = 0; i < size; i++) {
            tableColumnsList.add(createColumn(i, colType));
        }
    }

    public TableColumn createColumn(int i, final TableColumnsTypes colType) {
        TableColumn col = new TableColumn(colType.name + i);
        final int j = i;
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, Integer>, IntegerProperty>() {
            @Override
            public IntegerProperty call(TableColumn.CellDataFeatures<ObservableList, Integer> param) {
                InputTableRow someRow = (InputTableRow) param.getValue();
                if (colType.equals(TableColumnsTypes.WX)) {
                    return someRow.getWxBitSet().size() > j ? someRow.getWxBitSet().get(j) : null;
                } else {
                    return someRow.getWyBitSet().size() > j ? someRow.getWyBitSet().get(j) : null;
                }
            }
        });
        col.setCellFactory(cellFactory);
        col.setPrefWidth(30.0);
        return col;
    }

    public void updateRowQuantity(int oldVal, int newVal) {
        if (oldVal < newVal) {
            int delta = newVal * newVal - oldVal * oldVal;
            if (oldVal == 1){
                --delta;
            }
            for (int i = 0; i < delta; i++) {
                inputRows.add(getEmptyRow(newVal, inputRows.get(0).getWyBitSet().size()));
            }
        } else {
            int delta = oldVal * oldVal - newVal * newVal;
            if (inputRows.size() - delta == 1){
                --delta;
            }
            inputRows.remove(inputRows.size() - delta, inputRows.size());
        }
    }
    public InputTableRow getEmptyRow(int x, int y) {
        InputTableRow row = new InputTableRow();
        for (int i = 0; i < x; i++)
            row.getWxBitSet().add(new SimpleIntegerProperty(0));
        for (int i = 0; i < y; i++)
            row.getWyBitSet().add(new SimpleIntegerProperty(0));
        return row;
    }

}
