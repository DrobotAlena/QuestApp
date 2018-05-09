package nsu.fit.questapp.presenter;

import android.support.annotation.DrawableRes;

/**
 * Created by Alena Drobot
 */
public interface Presenter {

    @DrawableRes
    int getPicture(String name);

    String getDescription(String name);
}
