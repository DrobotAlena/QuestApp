package nsu.fit.questapp.model.deserializer;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import nsu.fit.questapp.model.card.Quest;
import nsu.fit.questapp.model.json.JsonReader;

import static nsu.fit.questapp.view.gallary.GalleryCardFragment.DEBATES;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.SPACE;

/**
 * Created by Alena Drobot
 */
public class GsonDeserializer {

    private final static Quest EMPTY_QUEST = null;

    private Gson gson;
    private JsonReader jsonReader;

    public GsonDeserializer(Context context) {
        gson = new GsonBuilder().create();
        jsonReader = new JsonReader(context);
    }

    @Nullable
    public Quest deserializeCard(String name) {
        switch (name) {
            case SPACE:
                return gson.fromJson(jsonReader.getSpaceJson(), Quest.class);
            case DEBATES:
                return gson.fromJson(jsonReader.getDebatesJson(), Quest.class);
            default:
                return EMPTY_QUEST;
        }
    }

    @Nullable
    public Quest deserializeCustomCard(Uri uri) {
        try {
            return gson.fromJson(jsonReader.getJsonFromStorage(uri), Quest.class);
        } catch (IOException exception) {
            Log.e("ERROR", "IOException");
            return null;
        }
    }
}
