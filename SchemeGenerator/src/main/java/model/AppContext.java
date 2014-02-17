package model;

import controller.MainScreenController;
import controller.utils.LocaleManager;
import javafx.scene.layout.Pane;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/11/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppContext extends BaseModel {
    private static AppContext instance;
    private static MainScreenController mainScreenController;
    private static Locale currentLocale;

    private Pane RootPane;

    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
            currentLocale = LocaleManager.UKRAINIAN;
        }
        return instance;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
        setChanged();
        notifyObservers(currentLocale);
    }

    public Pane getRootPane() {
        return RootPane;
    }

    public void setRootPane(Pane pane) {
        this.RootPane = pane;
        mainScreenController = new MainScreenController(this);
    }

    public MainScreenController getMainScreenController() {
        return mainScreenController;
    }

    public void setMainScreenController(MainScreenController controller) {
        mainScreenController = controller;
    }


}
