package model.equationModel;

import controller.utils.Utils;

/**
 * Created by Volodymyr_Kychak on 7/14/14.
 */
public enum ElementsType {

    A("A"), F("F"), T("T"), FILTER_L("F_L"), FILTER_R("F_R"), FILTER_H("F_H"),
    INPUT_SIGNALS("INPUT_SIGNALS"), OUTPUT_SIGNALS("OUTPUT_SIGNALS"),  SUPPLEMENTARY_SIGNALS("SUPPLEMENTARY_SIGNALS");
    private String name;

    ElementsType(String s) {
        name = s;
    }

    public String toString() {
        return Utils.getMessages().getString(name);
    }

    public String toNotLocaleString() {
        return name;
    }

}
