package Controller.Utils;

import Model.AppContext;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 10/11/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
   public static ResourceBundle getMessages(){
       Properties props = new Properties();
        return ResourceBundle.getBundle("Bundle/Messages/MessagesBundle", AppContext.getInstance().getCurrentLocale());
   }

}
