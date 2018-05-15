package nsu.fit.questapp.presenter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;

import nsu.fit.questapp.model.Model;
import nsu.fit.questapp.model.card.CardData;
import nsu.fit.questapp.view.QuestView;

/**
 * Created by Alena Drobot
 */
public class QuestPresenter implements Presenter {

    private final static String QUEST_LOADING_ERROR = "Проблема загрузки игры";
    private QuestView view;
    private Model model;
    private CardData card;

    public QuestPresenter(QuestView view, Context context) {
        this.view = view;
        this.model = new Model(context);
    }

    @Nullable
    @Override
    public CardData getCard(String name, int cardId) {
        card = model.getCardFromQuest(name, cardId);
        return checkAndGetCard();
    }

    @Nullable
    @Override
    public CardData getCardFromStorage(Uri uri, int cardId) {
        card = model.getCardFromCustomQuest(uri, cardId);
        return checkAndGetCard();
    }

    @Nullable
    private CardData checkAndGetCard() {
        if (card == null) {
            view.showError(QUEST_LOADING_ERROR);
        }
        return card;
    }

    @Nullable
    @Override
    public String getQuestTitle(String name) {
        return model.getTitle(name);
    }

    @Nullable
    @Override
    public String getQuestTitleFromStorage(Uri uri) {
        return model.getTitleFromStorage(uri);
    }
}
