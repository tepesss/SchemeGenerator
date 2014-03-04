package controller.truthTableTemplates.serialization;

import com.google.gson.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.lang.reflect.Type;

/**
 * Created by Volodymyr_Kychak on 2/25/14.
 */
public class IntegerPropertyAdapter implements JsonSerializer<IntegerProperty>, JsonDeserializer<IntegerProperty> {
    private static final String PROPERTY_VALUE = "value";

    @Override
    public JsonElement serialize(IntegerProperty integerProperty, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty(PROPERTY_VALUE, integerProperty.getValue());
        return jsonObj;
    }

    @Override
    public IntegerProperty deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObj = (JsonObject) jsonElement;
        int propertyValue = jsonObj.getAsJsonPrimitive(PROPERTY_VALUE).getAsInt();
        IntegerProperty property = new SimpleIntegerProperty(propertyValue);
        return property;
    }
}
