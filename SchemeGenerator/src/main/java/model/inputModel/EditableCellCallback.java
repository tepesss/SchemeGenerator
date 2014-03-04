package model.inputModel;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Created by Volodymyr_Kychak on 2/27/14.
 */
public class EditableCellCallback implements Callback<TableColumn, TableCell> {
    public javafx.scene.control.TableCell call(javafx.scene.control.TableColumn p) {
        return new EditableCell();
    }
}
