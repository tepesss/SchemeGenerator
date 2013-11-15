package View;

import Controller.MainScreenController;
import Controller.Utils.LocaleManager;
import Controller.Utils.Utils;
import Model.AppContext;
import View.Components.BaseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/31/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame implements ActionListener, Observer {

    JMenuBar menuBar;
    JMenu menuFile, subMunulanguage = new JMenu();
    public JCheckBoxMenuItem menuItemRU, menuItemUK, menuItemEN;
    BaseView currentComponent;
    AppContext model;
    MainScreenController controller;

    public MainFrame(AppContext argModel, MainScreenController argController){

        model = argModel;
        model.addObserver(this);
        controller = argController;
        createView();
    }

    private void createView(){
        currentComponent = controller.getInputScreen();
        currentComponent.createControls();
        setSize(320, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenuBar();
        add(currentComponent);
        setLabels();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.changeLocale(e.getSource());
    }

    private void createMenuBar(){
        menuBar = new JMenuBar();
        menuFile = new JMenu();
        menuItemRU = new JCheckBoxMenuItem();
        menuItemUK = new JCheckBoxMenuItem();
        menuItemEN =  new JCheckBoxMenuItem();
        menuItemRU.addActionListener(this);
        menuItemUK.addActionListener(this);
        menuItemEN.addActionListener(this);
        subMunulanguage = new JMenu();
        setJMenuBar(menuBar);
        menuFile.add(subMunulanguage);
        subMunulanguage.add(menuItemEN);
        subMunulanguage.add(menuItemRU);
        subMunulanguage.add(menuItemUK);
        selectCurrentLocale();
        menuBar.add(menuFile);
    }

    public void selectCurrentLocale(){
        Locale locale = AppContext.getInstance().getCurrentLocale();
        menuItemUK.setSelected(false);
        menuItemRU.setSelected(false);
        menuItemEN.setSelected(false);
        if(locale == LocaleManager.ENGLISH){
          menuItemUK.setSelected(true);
        } else if(locale == LocaleManager.RUSSIAN){
            menuItemRU.setSelected(true);
        } else{
            menuItemUK.setSelected(true);
        }
    }

    public void setLabels(){
        setTitle(Utils.getMessages().getString("appName"));
        menuFile.setText(Utils.getMessages().getString("file"));
        subMunulanguage.setText(Utils.getMessages().getString("language"));
        menuItemRU.setText(Utils.getMessages().getString("languageRu"));
        menuItemEN.setText(Utils.getMessages().getString("languageEng"));
        menuItemUK.setText(Utils.getMessages().getString("languageUkr"));
        currentComponent.setLabels();
    }

    public void showOutputScreen(BaseView view){
        remove(currentComponent);
        currentComponent = view;
        add(currentComponent);
        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Locale){
            selectCurrentLocale();
            setLabels();
        }
    }
}