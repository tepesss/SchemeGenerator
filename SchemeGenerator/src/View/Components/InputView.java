package View.Components;

import Controller.InputController;
import Controller.Utils.Utils;
import Model.AppContext;
import Model.EditableTableModel;
import Model.InputModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/12/13
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputView extends BaseView implements ActionListener {
    JButton generateBtn;
    JSpinner xSpinner;
    JSpinner ySpinner;
    JTable inputTable;
    int spinnerMaxValue = 10;

    public InputView(InputModel argModel, InputController argInputController) {
        model = argModel;
        controller = argInputController;
    }

    public void setLabels() {
        generateBtn.setText(Utils.getMessages().getString("generate"));
    }

    @Override
    public void createControls() {
        initComponents();
        bindListeners();
        setLayout(layout);
        addToLayout();
    }

    private void initComponents() {
        layout = new GridBagLayout();
        generateBtn = new JButton();
        inputTable = new JTable();

        TableModel tModel = new EditableTableModel(((InputModel) model).columnTitles, ((InputModel) model).dataEntries);
        inputTable = new JTable(tModel);
        inputTable.createDefaultColumnsFromModel();
        String[] Education = {"PG", "Msc", "B-Tech,", "Bsc", "12th", "10th"};
        JComboBox comboBox = new JComboBox(Education);
        inputTable.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBox));
        xSpinner = new JSpinner(new SpinnerNumberModel(1, 1, spinnerMaxValue, 1));
        ySpinner = new JSpinner(new SpinnerNumberModel(1, 1, spinnerMaxValue, 1));
    }

    private void bindListeners() {
        xSpinner.addChangeListener(spinnersChangeListener);
        ySpinner.addChangeListener(spinnersChangeListener);
        generateBtn.addActionListener(this);
    }

    private void addToLayout() {
        add(xSpinner);
        add(ySpinner);
        add(generateBtn);
        add(inputTable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateBtn) {
            AppContext.getInstance().getMainScreenController().generateScheme();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    ChangeListener spinnersChangeListener = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource() == xSpinner) {
                System.out.println("xSpinner " + xSpinner.getValue());
            } else if (e.getSource() == ySpinner) {
                System.out.println("ySpinner " + ySpinner.getValue());
            }
        }
    };
}
