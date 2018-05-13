package nsu.fit.questapp.model.deserializer;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import nsu.fit.questapp.model.card.Quest;
import nsu.fit.questapp.model.json.JsonReader;

import static nsu.fit.questapp.view.gallary.GalleryCardFragment.CUSTOM;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.DEBATES;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.SPACE;

public class GsonDeserializer {

    private final static Quest EMPTY_QUEST = null;

    private Gson gson;
    private JsonReader jsonReader;

    public GsonDeserializer(Context context) {
        gson = new GsonBuilder().create();
        jsonReader = new JsonReader(context);
    }

    @Nullable
    public Quest deserialize(String name) {
        switch (name) {
            case SPACE:
                return gson.fromJson(jsonReader.getSpaceJson(), Quest.class);
            case DEBATES:
                return gson.fromJson(jsonReader.getDebatesJson(), Quest.class);
            case CUSTOM:
                return gson.fromJson(jsonReader.getTestJson(), Quest.class);
            default:
                return EMPTY_QUEST;
        }
    }
}
