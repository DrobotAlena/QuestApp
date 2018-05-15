package nsu.fit.questapp.model;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import nsu.fit.questapp.model.card.CardData;
import nsu.fit.questapp.model.card.Quest;
import nsu.fit.questapp.model.deserializer.GsonDeserializer;
import nsu.fit.questapp.utils.StringUtils;

/**
 * Created by Alena Drobot
 */
public class Model {
    private GsonDeserializer deserializer;
    private Quest quest = null;
    private String localQuestName = null;
    private Uri customQuestUri = null;

    public Model(Context context) {
        this.deserializer = new GsonDeserializer(context);
    }

    @Nullable
    public CardData getCardFromQuest(@NonNull String name, int cardId) {
        if (quest == null && !name.equals(localQuestName)) {
            localQuestName = name;
            quest = deserializer.deserializeCard(name);
        }
        return checkAndGetCard(cardId);
    }

    @Nullable
    public CardData getCardFromCustomQuest(@NonNull Uri uri, int cardId) {
        if (quest == null && !uri.equals(customQuestUri)) {
            customQuestUri = uri;
            quest = deserializer.deserializeCustomCard(uri);
        }
        return checkAndGetCard(cardId);
    }

    @Nullable
    public String getTitle(@NonNull String name) {
        if (quest == null && !name.equals(localQuestName)) {
            localQuestName = name;
            quest = deserializer.deserializeCard(name);
        }
        return checkAndGetTitle();
    }

    @Nullable
    public String getTitleFromStorage(@NonNull Uri uri) {
        if (quest == null && !uri.equals(customQuestUri)) {
            customQuestUri = uri;
            quest = deserializer.deserializeCustomCard(uri);
        }
        return checkAndGetTitle();
    }

    @Nullable
    private CardData checkAndGetCard(int cardId) {
        if (quest == null || quest.getCards() == null || quest.getCards().size() <= cardId) {
            return null;
        } else {
            return quest.getCards().get(cardId);
        }
    }

    @Nullable
    private String checkAndGetTitle() {
        if (quest == null || StringUtils.isEmpty(quest.getTitle())) {
            return null;
        } else {
            return quest.getTitle();
        }
    }
}
