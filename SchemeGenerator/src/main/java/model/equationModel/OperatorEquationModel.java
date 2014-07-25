package model.equationModel;

import controller.builder.InputEquationBuilder;
import controller.builder.OperatorEquationBuilder;
import controller.builder.OutputEquationBuilder;
import model.BaseModel;
import model.correspondenceTables.CorrespondenceTablesModel;

/**
 * Created by Volodymyr_Kychak on 6/26/14.
 */
public class OperatorEquationModel extends BaseModel {
    private InputEquation input;
    private OperatorEquation operator;
    private OutputEquation output;

    public OperatorEquationModel() {
        buildInputEquation();
        buildOperatorEquation();
        buildOutputEquation();
    }

    private void buildInputEquation(){
        input = new InputEquationBuilder().build();
    }

    private void buildOperatorEquation(){
        operator = new OperatorEquationBuilder().build();
    }

    private void buildOutputEquation(){
        output = new OutputEquationBuilder().build();
    }
}
