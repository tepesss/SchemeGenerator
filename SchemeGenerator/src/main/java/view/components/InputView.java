package view.components;

import controller.InputController;
import controller.utils.Utils;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import jfxtras.labs.scene.control.ListSpinner;
import model.AppContext;
import model.inputModel.InputModel;

import java.util.Observable;

//import jfxtras.labs.scene.control.ListSpinner;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/12/13
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputView extends BaseView implements EventHandler , InvalidationListener {

    Button generateBtn;
    ListSpinner<Integer> spinnerWx, spinnerWy;
    int maxSpinnerValue = 10;
    Label WxLabel, WyLabel;

    public InputView(InputModel argModel, InputController argInputController) {
        model = argModel;
        controller = argInputController;
        createControls();
    }

    @Override
    public void createControls() {
        initComponents();
        bindListeners();
        addToPane();
    }

    private void initComponents() {
        generateBtn = new Button();
        spinnerWx = createSpinnerInteger();
//        spinnerWy = createSpinnerInteger();
    }

    private void addToPane() {
        getChildren().add(spinnerWx);
        getChildren().add(generateBtn);
    }

    public void setLabels() {
        generateBtn.setText(Utils.getMessages().getString("generate"));
    }

    private void bindListeners() {
        generateBtn.setOnAction(this);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void handle(Event event) {
        if (event.getSource() == generateBtn) {
//            AppContext.getInstance().getMainScreenController().generateScheme();
        }
    }

    private ListSpinner<Integer> createSpinnerInteger() {
        ListSpinner<Integer> spinner = new ListSpinner<Integer>(1, maxSpinnerValue);
        spinner.withArrowPosition(ListSpinner.ArrowPosition.SPLIT);
        spinner.withArrowDirection(ListSpinner.ArrowDirection.HORIZONTAL);
        spinner.valueProperty().addListener(this);
        return spinner;
    }


    @Override
    public void invalidated(javafx.beans.Observable observable) {
        ObjectProperty property = (ObjectProperty) observable;
        if (property.getBean() == spinnerWx) {
            property.getValue();
            System.out.println("spinnerWx" + property.getValue());
        } else if (property.getBean() == spinnerWy) {
            System.out.println("spinnerWy" + property.getValue());
        }
    }


}
