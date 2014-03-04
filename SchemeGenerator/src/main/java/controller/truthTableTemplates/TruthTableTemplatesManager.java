package controller.truthTableTemplates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.truthTableTemplates.serialization.CallbackInstanceCreator;
import controller.truthTableTemplates.serialization.InputTableRowListAdapter;
import controller.truthTableTemplates.serialization.IntegerPropertyAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import model.inputModel.InputModel;

/**
 * Created by Volodymyr_Kychak on 2/17/14.
 */
public class TruthTableTemplatesManager {
    private static final TruthTableTemplatesManager INSTANCE = new TruthTableTemplatesManager();

    private TruthTableTemplateTypes template;

    private TruthTableTemplatesManager() {
    }

    public static synchronized TruthTableTemplatesManager getInstance() {
        return INSTANCE;
    }

    public TruthTableTemplateTypes getTemplate() {
        return template;
    }

    public InputModel setTemplate(TruthTableTemplateTypes template) {
        this.template = template;
        return getModel();
    }

    public InputModel getModel() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IntegerProperty.class, new IntegerPropertyAdapter());
        builder.registerTypeAdapter(ObservableList.class, new InputTableRowListAdapter());
        builder.registerTypeAdapter(javafx.util.Callback.class, new CallbackInstanceCreator());
        Gson gson = builder.create();
        String modelString = "{\"inputRows\":[{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"}],\"wxQuantity\":{\"value\":2},\"wyQuantity\":{\"value\":1},\"cellFactory\":{},\"changed\":false,\"obs\":[]}";
        ;
        switch (template) {
            case AND:
                modelString = "{\"inputRows\":[{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"}],\"wxQuantity\":{\"value\":2},\"wyQuantity\":{\"value\":1},\"cellFactory\":{},\"changed\":false,\"obs\":[]}";
                break;
            case OR:
                modelString = "{\"inputRows\":[{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"}],\"wxQuantity\":{\"value\":2},\"wyQuantity\":{\"value\":1},\"cellFactory\":{},\"changed\":false,\"obs\":[]}";
                break;
            case NOT:
                modelString = "{\"inputRows\":[{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"},{\"TABLE_ROW\":\"{\\\"wxBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":0,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}],\\\"wyBitSet\\\":[{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}},{\\\"name\\\":\\\"\\\",\\\"value\\\":1,\\\"valid\\\":true,\\\"helper\\\":{\\\"observable\\\":{}}}]}\"}],\"wxQuantity\":{\"value\":2},\"wyQuantity\":{\"value\":2},\"cellFactory\":{},\"changed\":false,\"obs\":[]}";
                break;
        }

        InputModel newModel = gson.fromJson(modelString, InputModel.class);
        return newModel;
    }


}
