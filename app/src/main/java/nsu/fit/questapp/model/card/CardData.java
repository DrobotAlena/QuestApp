package nsu.fit.questapp.model.card;

import android.support.annotation.DrawableRes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Alena Drobot
 */
public class CardData {

    @SerializedName("id")
    private int id;
    @SerializedName("description")
    private String description;
    @SerializedName("drawableId")
    private int drawableId;
    @SerializedName("buttons")
    private ArrayList<ButtonData> buttons;

    public CardData(int id, String description, @DrawableRes int drawableId, ArrayList<ButtonData> buttons) {
        this.id = id;
        this.description = description;
        this.drawableId = drawableId;
        this.buttons = buttons;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @DrawableRes
    public int getDrawableId() {
        return drawableId;
    }

    public ArrayList<ButtonData> getButtons() {
        return buttons;
    }
}
