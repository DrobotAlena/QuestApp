package nsu.fit.questapp.model.card;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alena Drobot
 */
public class ButtonData {

    @SerializedName("text")
    private String text;
    @SerializedName("reference")
    private int reference;

    public ButtonData (String text, int reference) {
        this.text = text;
        this.reference = reference;
    }

    @Nullable
    public String getText() {
        return text;
    }

    public int getReference() {
        return reference;
    }
}
