package nsu.fit.questapp.model;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import nsu.fit.questapp.model.card.CardData;
import nsu.fit.questapp.model.card.Cards;
import nsu.fit.questapp.model.deserializer.GsonDeserializer;
import nsu.fit.questapp.presenter.Presenter;

/**
 * Created by Alena Drobot
 */
public class Model {

    private final static String SPACE = "space";
    private final static String DEBATES = "debates";

    private final Presenter presenter;

    private GsonDeserializer deserializer;

    public Model(Presenter presenter, Context context) {
        this.presenter = presenter;
        this.deserializer = new GsonDeserializer(context);
    }

    @DrawableRes
    public int getPictureId(String name) {
        // TODO
        return 0;
    }

    @Nullable
    public Cards getCards(String name) {
        return deserializer.deserialize(name);
    }
}
