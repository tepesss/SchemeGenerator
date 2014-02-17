package controller;

import com.google.gson.Gson;
import controller.utils.Utils;
import javafx.beans.*;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import jfxtras.labs.scene.control.ListSpinner;
import model.AppContext;
import model.inputModel.InputModel;
import model.inputModel.InputTableRow;
import view.components.InputView;


import java.io.*;
import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputController extends BaseController {


    @FXML
    private TableView<InputTableRow> inputTable;
    @FXML
    private Button generateBtn;
    @FXML
    private ListSpinner<Integer> spinnerWx, spinnerWy;
    @FXML
    private TextField textWx, textWy;
    private ResourceBundle bundle;

    InputModel model;
    InputView view;
    int maxSpinnerValue = 10;
    private ChangeListener<Integer> invalidationListener = new ChangeListener<Integer>() {

        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer integer2) {
            ObjectProperty property = (ObjectProperty) observableValue;
            if (property.getBean() == spinnerWx) {
                model.updateRowQuantity(integer, integer2);
                changeAllRow(integer2 - integer, 0);
            } else if (property.getBean() == spinnerWy) {
                changeAllRow(0, integer2 - integer);
            }
        }
    };

    public InputController() {
        model = new InputModel();
    }

    public Pane getView() {
        return view;
    }

    public void init() {
        int x = model.getWxQuantityVal();
        int y = model.getWyQuantityVal();
        populateListSpinner(spinnerWx, 1, maxSpinnerValue, model.getWxQuantityVal());
        populateListSpinner(spinnerWy, 1, maxSpinnerValue, model.getWyQuantityVal());
        bindEvents();
        for (int i = 0; i < x * y; i++) {
            model.getInputRows().add(model.getEmptyRow(x, y));
        }
        inputTable.setEditable(true);
        inputTable.getColumns().addAll(model.getColumns());
        inputTable.setItems(model.getInputRows());
        inputTable.setMaxHeight(Double.MAX_VALUE);
        inputTable.setMaxWidth(Double.MAX_VALUE);
    }

    private void bindEvents(){
        model.getWxQuantity().bind(spinnerWx.valueProperty());
        model.getWyQuantity().bind(spinnerWy.valueProperty());
        spinnerWx.valueProperty().addListener(invalidationListener);
        spinnerWy.valueProperty().addListener(invalidationListener);
        generateBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AppContext.getInstance().getMainScreenController().nextScreen();
            }
        });
    }



    private void populateListSpinner(ListSpinner spinner, int first, int last, int value) {
        ObservableList<Integer> itemsList = FXCollections.observableArrayList();
        List<Integer> valuesList = Utils.createIntList(first, last);
        Integer[] array = new Integer[valuesList.size()];
        valuesList.toArray(array);
        itemsList.addAll(array);
        spinner.setItems(itemsList);
        spinner.withArrowPosition(ListSpinner.ArrowPosition.SPLIT);
        spinner.withArrowDirection(ListSpinner.ArrowDirection.HORIZONTAL);
        spinner.setValue(value);
        spinner.requestLayout();
    }

    private void changeAllRow(int deltaX, int deltaY) {
        if (deltaX != 0) {
            if (deltaX < 0) {
                for (InputTableRow row : model.getInputRows()) {
                    row.getWxBitSet().remove(row.getWxBitSet().removeLast());
                }
                inputTable.getColumns().remove(model.getInputRows().get(0).getWxBitSet().size());
            }else if(deltaX >0){
                for (InputTableRow row : model.getInputRows()) {
                    row.getWxBitSet().addLast(new SimpleIntegerProperty(0));
                }
                inputTable.getColumns().add(model.getWxQuantityVal()-1, model.createColumn(model.getWxQuantityVal()-1, InputModel.TableColumnsTypes.WX));
            }
        } else if (deltaY != 0) {
            if (deltaY < 0) {
                for (InputTableRow row : model.getInputRows()) {
                    row.getWyBitSet().remove(row.getWyBitSet().removeLast());
                }
                inputTable.getColumns().remove(inputTable.getColumns().size() - 1);
            } else if (deltaY > 0) {
                for (InputTableRow row : model.getInputRows()) {
                    row.getWyBitSet().addLast(new SimpleIntegerProperty(0));
                }
                inputTable.getColumns().add(model.createColumn(model.getWyQuantityVal()-1, InputModel.TableColumnsTypes.WY));
            }
        }
    }



}
