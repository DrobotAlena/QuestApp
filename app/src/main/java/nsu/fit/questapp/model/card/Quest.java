package nsu.fit.questapp.model.card;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Quest {

    @SerializedName("cards")
    ArrayList<CardData> cards;

    public Quest(ArrayList<CardData> cards) {
        this.cards = cards;
    }

    @Nullable
    public ArrayList<CardData> getCards() {
        return cards;
    }
}
