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
        input = new InputEquationBuilder().build(this);
    }

    private void buildOperatorEquation(){
        operator = new OperatorEquationBuilder().build(this);
    }

    private void buildOutputEquation(){
        output = new OutputEquationBuilder().build(this);
    }

    public InputEquation getInput() {
        return input;
    }

    public void setInput(InputEquation input) {
        this.input = input;
    }

    public OperatorEquation getOperator() {
        return operator;
    }

    public void setOperator(OperatorEquation operator) {
        this.operator = operator;
    }

    public OutputEquation getOutput() {
        return output;
    }

    public void setOutput(OutputEquation output) {
        this.output = output;
    }
}
