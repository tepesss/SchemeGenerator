package controller;

import model.AppContext;
import model.OutputModel;
import model.combinedTable.CombinedTableModel;
import model.correspondenceTables.CorrespondenceTablesModel;
import model.equationModel.OperatorEquationModel;
import model.inputModel.InputModel;
import model.inputModel.InputTableRow;

import java.util.List;

/**
 * Created by Volodymyr_Kychak on 14.06.2014.
 */
public class SchemeGeneratorEngine {
    public OutputModel generateOutputModel(){
        OutputModel outputModel = new OutputModel();
        buildOperatorDescription();
        AppContext.getInstance().addModel(outputModel);
        return outputModel;
    }

    private void buildOperatorDescription(){
        CorrespondenceTablesModel correspondenceTablesModel =  buildCorrespondenceTables();
        OperatorEquationModel operatorEquationModel = new OperatorEquationModel(correspondenceTablesModel);
    }

    private CorrespondenceTablesModel buildCorrespondenceTables(){
        final CombinedTableModel combinedTableModel = buildCombinedTable();
        return new CorrespondenceTablesModel(combinedTableModel);
    }

    private CombinedTableModel buildCombinedTable(){
        final InputModel inputModel;
        inputModel = (InputModel)AppContext.getInstance().getModels().get(InputModel.class.getName());
        final List<InputTableRow> inputRows = inputModel.getInputRows();
        return new CombinedTableModel(inputRows);
    }
}
