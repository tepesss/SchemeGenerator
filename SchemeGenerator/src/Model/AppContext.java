package Model;

import Controller.Utils.LocaleManager;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/11/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class AppContext {
    public static AppContext instance;
    private static Locale currentLocale;

    public AppContext(){
        currentLocale = LocaleManager.UKRAINIAN;
    }

    public static synchronized AppContext getInstance(){
        if(instance == null){
            instance = new AppContext();
        }
        return instance;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        AppContext.currentLocale = currentLocale;
    }

}
