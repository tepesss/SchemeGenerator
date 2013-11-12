package View;

import Controller.Utils.LocaleManager;
import Controller.Utils.Utils;
import Model.AppContext;
import View.Components.BaseComponent;
import View.Components.InputComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/31/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame implements ActionListener{

    JMenuBar menuBar;
    JMenu menuFile, subMunulanguage = new JMenu();
    JCheckBoxMenuItem menuItemRU, menuItemUK, menuItemEN;
    BaseComponent currentComponent;

    @Override
    protected void frameInit() {
        super.frameInit();
        currentComponent = new InputComponent();
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
        changeLocale(e.getSource());
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

    private void selectCurrentLocale(){
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

    private void changeLocale(Object element){
        if (element==menuItemRU){
            AppContext.getInstance().setCurrentLocale(LocaleManager.RUSSIAN);
        }   else if (element==menuItemUK){
            AppContext.getInstance().setCurrentLocale(LocaleManager.UKRAINIAN);
        }else{
            AppContext.getInstance().setCurrentLocale(LocaleManager.ENGLISH);
        }
        selectCurrentLocale();
        setLabels();
    }

    private void setLabels(){
        setTitle(Utils.getMessages().getString("appName"));
        menuFile.setText(Utils.getMessages().getString("file"));
        subMunulanguage.setText(Utils.getMessages().getString("language"));
        menuItemRU.setText(Utils.getMessages().getString("languageRu"));
        menuItemEN.setText(Utils.getMessages().getString("languageEng"));
        menuItemUK.setText(Utils.getMessages().getString("languageUkr"));
        currentComponent.setLabels();
    }
}