package Model;

import Controller.InputController;
import Controller.MainScreenController;
import Controller.Utils.LocaleManager;
import View.Components.BaseView;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/11/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppContext extends BaseModel {
    public static AppContext instance;
    private static MainScreenController mainScreenController;
    private static Locale currentLocale;

    public static synchronized AppContext getInstance(){
        if(instance == null){
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

    public MainScreenController getMainScreenController(){
        return mainScreenController;
    }


}
