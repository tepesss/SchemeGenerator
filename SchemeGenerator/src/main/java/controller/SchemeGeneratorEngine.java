package controller;

import javafx.scene.image.Image;
import model.AppContext;
import model.OutputModel;
import model.combinedTable.CombinedTablesModel;
import model.correspondenceTables.CorrespondenceTablesModel;
import model.equationModel.OperatorEquationModel;
import model.inputModel.InputModel;

/**
 * Created by Volodymyr_Kychak on 14.06.2014.
 */
public class SchemeGeneratorEngine {
    public OutputModel generateOutputModel(){
        OutputModel outputModel = new OutputModel();
        outputModel.setEquationModel(buildOperatorDescription());
        AppContext.getInstance().addModel(outputModel);
        return outputModel;
    }

    private OperatorEquationModel buildOperatorDescription(){
        CorrespondenceTablesModel correspondenceTablesModel =  buildCorrespondenceTables();
        OperatorEquationModel operatorEquationModel = new OperatorEquationModel();
        AppContext.getInstance().addModel(operatorEquationModel);
        return operatorEquationModel;
    }

    private CorrespondenceTablesModel buildCorrespondenceTables(){
        final CombinedTablesModel combinedTableModel = buildCombinedTable();
        CorrespondenceTablesModel correspondenceTablesModel = new CorrespondenceTablesModel(combinedTableModel);
        AppContext.getInstance().addModel(correspondenceTablesModel);
        return correspondenceTablesModel;
    }

    private CombinedTablesModel buildCombinedTable(){
        final InputModel inputModel;
        inputModel = (InputModel)AppContext.getInstance().getModels().get(InputModel.class.getName());
        CombinedTablesModel correspondenceTablesModel =  new CombinedTablesModel(inputModel);
        AppContext.getInstance().addModel(correspondenceTablesModel);
        return new CombinedTablesModel(inputModel);
    }


}
