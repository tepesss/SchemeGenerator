package Controller;

import Controller.Utils.LocaleManager;
import Model.AppContext;
import Model.InputModel;
import Model.OutputModel;
import View.Components.BaseView;
import View.MainFrame;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreenController extends BaseController {
    private MainFrame view;
    private AppContext model;

    public MainScreenController(AppContext model) {
        this.model = model;
        view = new MainFrame(model, this);
    }

    public void generateScheme() {
        view.showScreen(getOutputScreen());
    }

    public void changeLocale(Object element) {
        if (element == view.menuItemRU) {
            model.setCurrentLocale(LocaleManager.RUSSIAN);
        } else if (element == view.menuItemUK) {
            model.setCurrentLocale(LocaleManager.UKRAINIAN);
        } else if (element == view.menuItemEN) {
            model.setCurrentLocale(LocaleManager.ENGLISH);
        }
    }

    public BaseView getInputScreen() {
        InputModel inputModel = new InputModel();
        InputController inputController = new InputController(inputModel);
        return inputController.getView();
    }

    public BaseView getOutputScreen() {
        OutputModel outputModel = new OutputModel();
        OutputController outputController = new OutputController(outputModel);
        return outputController.getView();
    }

}
