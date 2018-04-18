package nsu.fit.questapp.model.card;

import android.support.annotation.DrawableRes;

import java.util.ArrayList;

/**
 * Created by Alena Drobot
 */
public class CardData {

    private int id;
    private String description;
    private int drawableId;
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
