package nsu.fit.questapp.presenter;

import android.support.annotation.Nullable;

import nsu.fit.questapp.model.card.CardData;

/**
 * Created by Alena Drobot
 */
public interface Presenter {

    @Nullable
    CardData getCard(String name, int cardId);
}
