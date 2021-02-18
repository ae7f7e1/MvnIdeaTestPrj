package IdeaTestProject;

import java.lang.reflect.Type;
import java.time.LocalTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class LocalTimeAdapter implements JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        String[] timeArray = json.getAsString().split(":");
        return LocalTime.of(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
    }
    
}
