package nsu.fit.questapp.model.card;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Cards {

    @SerializedName("cards")
    ArrayList<CardData> cards;

    public Cards(ArrayList<CardData> cards) {
        this.cards = cards;
    }

    public ArrayList<CardData> getCards() {
        return cards;
    }
}
