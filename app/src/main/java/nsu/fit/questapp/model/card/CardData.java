package nsu.fit.questapp.model.card;

import android.support.annotation.Nullable;

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
    @SerializedName("drawableName")
    private String drawableName;
    @SerializedName("buttons")
    private ArrayList<ButtonData> buttons;

    public CardData(int id, String description, String drawableName, ArrayList<ButtonData> buttons) {
        this.id = id;
        this.description = description;
        this.drawableName = drawableName;
        this.buttons = buttons;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getDrawableName() {
        return drawableName;
    }

    @Nullable
    public ArrayList<ButtonData> getButtons() {
        return buttons;
    }
}
