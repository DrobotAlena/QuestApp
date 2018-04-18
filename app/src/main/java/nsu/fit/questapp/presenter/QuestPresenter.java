package nsu.fit.questapp.presenter;

import android.support.annotation.DrawableRes;

import nsu.fit.questapp.model.Model;
import nsu.fit.questapp.view.QuestView;

/**
 * Created by Alena Drobot
 */
public class QuestPresenter implements Presenter {

    private QuestView view;
    private Model model;

    public QuestPresenter(QuestView view) {
        this.view = view;
        this.model = new Model(this);
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

    private void sendError(String message) {
        view.showError(message);
    }
}
