package Controller;

import Model.OutputModel;
import View.Components.BaseView;
import View.Components.OutputView;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/31/13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class OutputController extends BaseController{
    private OutputModel model;
    private BaseView view;
    public OutputController(OutputModel argModel){
        model = argModel;
        view = new OutputView(model, this);
    }

    public BaseView getView() {
        return view;
    }
}
