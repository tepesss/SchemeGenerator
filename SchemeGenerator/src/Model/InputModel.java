package Model;

/**
 * Created with IntelliJ IDEA.
 * User: Volodymyr_Kychak
 * Date: 11/15/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputModel extends BaseModel{
    public String[] columnTitles = {"First Name", "Last Name", "weight", "Qualification", "age(18+)"};
    public Object[][] dataEntries = {
            {"ABHISHEK", "DUBEY", new Integer(50), "B-tech", new Boolean(false)},
            {"MANISH", "TIWARI", new Integer(80), "PG", new Boolean(true)},
            {"ANURUDDHA ", "PANDEY", new Integer(80), "Msc", new Boolean(true)},
            {"Bindresh", "AGINHOTRI", new Integer(80), "Bsc", new Boolean(true)},
            {"SOURABH", "TRIPATHI", new Integer(80), "PG", new Boolean(true)},
            {"AMIT", "GUPTA", new Integer(70), "Gratuate", new Boolean(false)},
            {"AMIT", "VERMA", new Integer(55), "12TH", new Boolean(true)},};

    public InputModel() {
    }


}
