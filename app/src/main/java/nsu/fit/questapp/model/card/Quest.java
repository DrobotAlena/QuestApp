package nsu.fit.questapp.model.card;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Alena Drobot
 */
public class Quest {

    @SerializedName("title")
    String title;
    @SerializedName("cards")
    ArrayList<CardData> cards;

    public Quest(String title, ArrayList<CardData> cards) {
        this.title = title;
        this.cards = cards;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public ArrayList<CardData> getCards() {
        return cards;
    }
}
