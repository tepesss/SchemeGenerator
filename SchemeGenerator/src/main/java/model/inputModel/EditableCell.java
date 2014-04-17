package model.inputModel;

import controller.truthTableTemplates.TruthTableTemplateTypes;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Created by Volodymyr_Kychak on 1/20/14.
 */
public class EditableCell extends TableCell<InputTableRow, Integer> {

    private TextField textField;

    public EditableCell() {
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }

        setGraphic(textField);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(String.valueOf(getItem()));
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setGraphic(textField);
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            } else {
                setText(getString());
                setContentDisplay(ContentDisplay.TEXT_ONLY);
            }
        }

    }

    private void createTextField() {
        textField = new TextField(getString()) {
            @Override
            public void replaceText(int i, int i2, String s) {
                setRestrictedValue(s);
            }

            @Override
            public void replaceSelection(String s) {
                setRestrictedValue(s);
            }

            private void setRestrictedValue(String s) {
                if (s.matches("[0-1]") || s.isEmpty()) {
                    if (s.isEmpty()) {
                        s = "0";
                    }
                    setText(s);
                }
            }
        };
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    commitEdit(Integer.parseInt(textField.getText()));
                    resetChoiceBox();
                }
            }
        });

        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(Integer.parseInt(textField.getText()));
                    resetChoiceBox();
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            }
        });
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

    private void resetChoiceBox() {
        ChoiceBox<TruthTableTemplateTypes> box = (ChoiceBox<TruthTableTemplateTypes>) getScene().lookup("#truthTableChoiceBox");
        if (box != null && box.getSelectionModel().getSelectedIndex() != 0) {
            box.getSelectionModel().select(0);
        }
    }
}