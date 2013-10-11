import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 9/23/13
 * Time: 10:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class SchemeGenerator {
//    public static void main (String[] args){
//        new MainScreen().show();
//    }
    static public void main(String[] args) {

    String language;
    String country;

        language = new String("en");
        country = new String("US");


    Locale currentLocale;
    ResourceBundle messages;

    currentLocale = new Locale(language, country);

    messages = ResourceBundle.getBundle("Bundle/Messages/MessagesBundle", currentLocale);
    System.out.println(messages.getString("greetings"));
}
}
