package controller.truthTableTemplates.serialization;

import com.google.gson.InstanceCreator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.inputModel.EditableCellCallback;
import model.inputModel.InputModel;

import java.lang.reflect.Type;

/**
 * Created by Volodymyr_Kychak on 2/27/14.
 */
public class CallbackInstanceCreator implements InstanceCreator<Callback<TableColumn, TableCell>> {
    @Override
    public Callback<TableColumn, TableCell> createInstance(Type type) {
        return new EditableCellCallback();
    }
}
