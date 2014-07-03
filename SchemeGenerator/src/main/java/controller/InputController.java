package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.truthTableTemplates.TruthTableTemplateTypes;
import controller.truthTableTemplates.TruthTableTemplatesManager;
import controller.truthTableTemplates.serialization.CallbackInstanceCreator;
import controller.truthTableTemplates.serialization.InputTableRowListAdapter;
import controller.truthTableTemplates.serialization.IntegerPropertyAdapter;
import controller.utils.Utils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import jfxtras.scene.control.ListSpinner;
import model.AppContext;
import model.inputModel.InputModel;
import model.inputModel.InputTableRow;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputController implements IBaseController {

    @FXML
    private TableView<InputTableRow> inputTable;
    @FXML
    private ChoiceBox<TruthTableTemplateTypes> truthTableChoiceBox;
    @FXML
    private Button generateBtn;
    @FXML
    private ListSpinner<Integer> spinnerWx, spinnerWy;
    @FXML
    private TextField textWx, textWy;
    private ResourceBundle bundle;

    InputModel model;
    int maxSpinnerValue = 10;
    private ChangeListener<Integer> invalidationListener = new ChangeListener<Integer>() {
        @Override
        public void changed(ObservableValue<? extends Integer> observableValue, Integer oldInteger, Integer newInteger) {
            ObjectProperty property = (ObjectProperty) observableValue;
            truthTableChoiceBox.getSelectionModel().select(0);
            if (property.getBean() == spinnerWx) {
                model.updateRowQuantity(oldInteger, newInteger);
                changeAllRow(newInteger - oldInteger, 0);
            } else if (property.getBean() == spinnerWy) {
                changeAllRow(0, newInteger - oldInteger);
            }
        }
    };

    public InputController() {
        model = new InputModel();
    }

    @Override
    public void init() {
        populateTruthTableChoiceBox();
        int x = model.getWxQuantityVal();
        int y = model.getWyQuantityVal();
        populateListSpinner(spinnerWx, 1, maxSpinnerValue, model.getWxQuantityVal());
        populateListSpinner(spinnerWy, 1, maxSpinnerValue, model.getWyQuantityVal());
        bindEvents();
        if (model.getInputRows().size() < 1) {
            for (int i = 0; i < x * y; i++) {
                model.getInputRows().add(model.getEmptyRow(x, y));
            }
        }
        inputTable.setEditable(true);
        model.getTableColumnsList().clear();
        model.createColumns();
        inputTable.getColumns().addAll(model.getColumnsAsArray());
        inputTable.setItems(model.getInputRows());
    }

    ChangeListener choiceBoxChangeListener = new ChangeListener<TruthTableTemplateTypes>() {
        @Override
        public void changed(ObservableValue<? extends TruthTableTemplateTypes> observableValue, TruthTableTemplateTypes oldTemplate, TruthTableTemplateTypes newTemplate) {
            if (newTemplate != null && newTemplate != TruthTableTemplateTypes.NO_TEMPLATE) {
                clean();
                model = TruthTableTemplatesManager.getInstance().setTemplate(newTemplate);
                init();
            }
        }
    };

    private void bindEvents() {
        model.getWxQuantity().bind(spinnerWx.valueProperty());
        model.getWyQuantity().bind(spinnerWy.valueProperty());
        spinnerWx.valueProperty().addListener(invalidationListener);
        spinnerWy.valueProperty().addListener(invalidationListener);
        truthTableChoiceBox.getSelectionModel().selectedItemProperty().addListener(choiceBoxChangeListener);
        generateBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                AppContext.getInstance().addModel(model);

                AppContext.getInstance().getMainScreenController().nextScreen();
                generateTemplateString();
            }
        });
    }

    private void clean() {
        unBind();
        inputTable.getColumns().clear();
    }

    private void unBind() {
        model.getWxQuantity().unbind();
        model.getWyQuantity().unbind();
        spinnerWx.valueProperty().removeListener(invalidationListener);
        spinnerWy.valueProperty().removeListener(invalidationListener);
    }

    private void populateTruthTableChoiceBox() {
        if (truthTableChoiceBox.getItems().size() < 1) {
            ObservableList<TruthTableTemplateTypes> list = FXCollections.observableArrayList();
            list.addAll(TruthTableTemplateTypes.NO_TEMPLATE,
                        TruthTableTemplateTypes.AND,
                        TruthTableTemplateTypes.NOT,
                        TruthTableTemplateTypes.OR,
                        TruthTableTemplateTypes.DESCRAMBLER);
            truthTableChoiceBox.setItems(list);
            truthTableChoiceBox.getSelectionModel().select(0);
        }
    }

    private void populateListSpinner(ListSpinner spinner, int first, int last, int value) {
        ObservableList<Integer> itemsList = FXCollections.observableArrayList();
        List<Integer> valuesList = Utils.createIntList(first, last);
        Integer[] array = new Integer[valuesList.size()];
        valuesList.toArray(array);
        itemsList.addAll(array);
        spinner.setItems(itemsList);



//        spinner.withArrowPosition(ListSpinner.ArrowPosition.SPLIT);
//        spinner.withArrowDirection(ListSpinner.ArrowDirection.HORIZONTAL);
        spinner.setValue(value);
        spinner.requestLayout();
    }

    private void changeAllRow(int deltaX, int deltaY) {
        if (deltaX != 0) {
            if (deltaX < 0) {
                for (InputTableRow row : model.getInputRows()) {
                    row.getWxList().remove(row.getWxList().removeLast());
                }
                inputTable.getColumns().remove(model.getInputRows().get(0).getWxList().size());
            } else if (deltaX > 0) {
                for (InputTableRow row : model.getInputRows()) {
                    row.getWxList().addLast(new SimpleIntegerProperty(0));
                }
                inputTable.getColumns().add(model.getWxQuantityVal() - 1, model.createColumn(model.getWxQuantityVal() - 1, InputModel.TableColumnsTypes.WX));
            }
        } else if (deltaY != 0) {
            if (deltaY < 0) {
                for (InputTableRow row : model.getInputRows()) {
                    row.getWyList().remove(row.getWyList().removeLast());
                }
                inputTable.getColumns().remove(inputTable.getColumns().size() - 1);
            } else if (deltaY > 0) {
                for (InputTableRow row : model.getInputRows()) {
                    row.getWyList().addLast(new SimpleIntegerProperty(0));
                }
                inputTable.getColumns().add(model.createColumn(model.getWyQuantityVal() - 1, InputModel.TableColumnsTypes.WY));
            }
        }
    }

    private void generateTemplateString() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IntegerProperty.class, new IntegerPropertyAdapter());
        builder.registerTypeAdapter(ObservableList.class, new InputTableRowListAdapter());
        builder.registerTypeAdapter(javafx.util.Callback.class, new CallbackInstanceCreator());
        Gson gson = builder.create();
        String modelS = gson.toJson(model, InputModel.class);
        System.out.println(modelS);
    }
}
