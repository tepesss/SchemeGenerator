package Controller;

import Model.InputModel;
import View.Components.InputView;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 10:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputController extends BaseController {
    InputModel model;
    InputView view;

    public InputController (InputModel argModel){
        model = argModel;
        view = new InputView(model, this );
    }

    public InputView getView() {
        return view;
    }
}
