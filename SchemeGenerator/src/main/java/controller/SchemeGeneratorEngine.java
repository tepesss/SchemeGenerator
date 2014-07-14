package controller;

import javafx.scene.image.Image;
import model.AppContext;
import model.OutputModel;
import model.combinedTable.CombinedTableModel;
import model.combinedTable.CombinedTablesModel;
import model.correspondenceTables.CorrespondenceTable;
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
        outputModel.setImage(buildScheme());
        AppContext.getInstance().addModel(outputModel);
        return outputModel;
    }

    private Image buildScheme(){
        OperatorEquationModel operatorEquationModel = buildOperatorDescription();
        Image scheme = new SchemeImageBuilder().buildImage(operatorEquationModel);
        return scheme;
    }

    private OperatorEquationModel buildOperatorDescription(){
        CorrespondenceTablesModel correspondenceTablesModel =  buildCorrespondenceTables();
        OperatorEquationModel operatorEquationModel = new OperatorEquationModel(correspondenceTablesModel);
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

        return new CombinedTablesModel(inputModel);
    }


}
