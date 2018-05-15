package nsu.fit.questapp.presenter;

import android.net.Uri;
import android.support.annotation.Nullable;

import nsu.fit.questapp.model.card.CardData;
import nsu.fit.questapp.utils.StringUtils;

/**
 * Created by Alena Drobot
 */
public interface Presenter {

    @Nullable
    CardData getCard(String name, int cardId);

    @Nullable
    CardData getCardFromStorage(Uri uri, int cardId);

    @Nullable
    String getQuestTitle(String name);

    @Nullable
    String getQuestTitleFromStorage(Uri uri);
}
