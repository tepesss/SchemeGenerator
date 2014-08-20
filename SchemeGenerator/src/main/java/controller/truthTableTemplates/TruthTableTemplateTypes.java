package controller.truthTableTemplates;

import controller.utils.Utils;

/**
 * Created by Volodymyr_Kychak on 2/18/14.
 */
public enum TruthTableTemplateTypes {

    NO_TEMPLATE("NO_TEMPLATE"), AND("AND"), AND_3("AND_3"), OR("OR"), NOT("NOT"), DESCRAMBLER("DESCRAMBLER");
    private String name;

    TruthTableTemplateTypes(String s) {
        name = s;
    }

    public String toString() {
        return Utils.getMessages().getString(name);
    }

    public String toNotLocaleString() {
        return name;
    }
}