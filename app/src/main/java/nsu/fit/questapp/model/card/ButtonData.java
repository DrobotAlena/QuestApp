package nsu.fit.questapp.model.card;

/**
 * Created by Alena Drobot
 */
public class ButtonData {

    private String text;
    private int reference;

    public ButtonData (String text, int reference) {
        this.text = text;
        this.reference = reference;
    }

    public String getText() {
        return text;
    }

    public int getReference() {
        return reference;
    }
}
