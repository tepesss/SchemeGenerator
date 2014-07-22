package model;

import javafx.scene.image.Image;
import model.equationModel.OperatorEquationModel;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class OutputModel extends BaseModel {
    private OperatorEquationModel equationModel;

    public OutputModel() {
    }

    public OperatorEquationModel getEquationModel() {
        return equationModel;
    }

    public void setEquationModel(OperatorEquationModel equationModel) {
        this.equationModel = equationModel;
    }

}
