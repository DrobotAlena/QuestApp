package nsu.fit.questapp.presenter;

import android.content.Context;
import android.support.annotation.DrawableRes;

import nsu.fit.questapp.model.Model;
import nsu.fit.questapp.model.card.Cards;
import nsu.fit.questapp.utils.StringUtils;
import nsu.fit.questapp.view.QuestView;

/**
 * Created by Alena Drobot
 */
public class QuestPresenter implements Presenter {

    private QuestView view;
    private Model model;
    private Cards cards;

    public QuestPresenter(QuestView view, Context context) {
        this.view = view;
        this.model = new Model(this, context);
    }

    @DrawableRes
    @Override
    public int getPicture(String name) {
        int pictureId = model.getPictureId(name);
        if (pictureId == 0) {
            sendError("Нет картинки");
            return 0;
        } else {
            return pictureId;
        }
    }

    // FINISH WORK
    @Override
    public String getDescription(String name) {
        if (cards == null) {
            cards = model.getCards(name);
        }
        String description = cards.getCards().get(0).getDescription();
        if (StringUtils.isEmpty(description)) {
            sendError("Пустое описание");
            return null;
        } else {
            return description;
        }
    }

    private void sendError(String message) {
        view.showError(message);
    }
}
