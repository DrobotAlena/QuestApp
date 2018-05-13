package nsu.fit.questapp.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import nsu.fit.questapp.model.Model;
import nsu.fit.questapp.model.card.CardData;
import nsu.fit.questapp.model.card.Quest;
import nsu.fit.questapp.view.QuestView;

/**
 * Created by Alena Drobot
 */
public class QuestPresenter implements Presenter {

    private QuestView view;
    private Model model;
    private Quest quest;

    public QuestPresenter(QuestView view, Context context) {
        this.view = view;
        this.model = new Model(this, context);
    }

    @Nullable
    @Override
    public CardData getCard(String name, int cardId) {
        if (quest == null) {
            quest = model.getQuest(name);
        }
        if (quest == null || quest.getCards() == null || quest.getCards().size() <= cardId) {
            view.showError("Проблема загрузки игры: " + name);
            return null;
        } else {
            return quest.getCards().get(cardId);
        }
    }
}
