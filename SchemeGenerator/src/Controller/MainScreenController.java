package Controller;

import View.MainView;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreenController {
    private MainView view;

    public void start() {
        view = new MainView();
        view.showContent();
    }

}
