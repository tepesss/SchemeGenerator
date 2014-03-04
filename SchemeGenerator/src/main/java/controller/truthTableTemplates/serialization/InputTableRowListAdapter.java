package controller.truthTableTemplates.serialization;

import com.google.gson.*;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.inputModel.InputTableRow;

import java.lang.reflect.Type;

/**
 * Created by Volodymyr_Kychak on 2/25/14.
 */
public class InputTableRowListAdapter implements JsonSerializer<ObservableList<InputTableRow>>, JsonDeserializer<ObservableList<InputTableRow>> {
    private static final String TABLE_ROW = "TABLE_ROW";
    @Override
    public JsonElement serialize(ObservableList<InputTableRow> inputTableRows, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        Gson gson = new Gson();
        for(InputTableRow tableRow : inputTableRows){
            JsonObject jsonObject = new JsonObject();
            String tableRowString = gson.toJson(tableRow, InputTableRow.class);
            jsonObject.addProperty(TABLE_ROW, tableRowString);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public ObservableList<InputTableRow> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ObservableList<InputTableRow> tableRowList = FXCollections.observableArrayList();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(IntegerProperty.class, new IntegerPropertyAdapter());
        Gson gson = builder.create();
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for(JsonElement element : jsonArray){
            JsonObject jsonObj = (JsonObject) element;
            String tableRowString = jsonObj.getAsJsonPrimitive(TABLE_ROW).getAsString();
            InputTableRow tableRow = gson.fromJson(tableRowString, InputTableRow.class);
            tableRowList.add(tableRow);
        }
        return tableRowList;
    }


}
