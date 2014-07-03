package controller;

import model.AppContext;
import model.OutputModel;
import model.combinedTable.CombinedTableModel;
import model.combinedTable.CombinedTablesModel;
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
        final CombinedTablesModel combinedTableModel = buildCombinedTable();
        return new CorrespondenceTablesModel(combinedTableModel);
    }

    private CombinedTablesModel buildCombinedTable(){
        final InputModel inputModel;
        inputModel = (InputModel)AppContext.getInstance().getModels().get(InputModel.class.getName());
        final List<InputTableRow> inputRows = inputModel.getInputRows();
        if(inputRows.get(0).getWyList().size()>1){
            for(InputTableRow row: inputRows){
                row.calculateZ0Value();
            }
        }
        return new CombinedTablesModel(inputRows);
    }
}
