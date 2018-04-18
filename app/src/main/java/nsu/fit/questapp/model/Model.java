package nsu.fit.questapp.model;

import android.support.annotation.DrawableRes;

import nsu.fit.questapp.presenter.Presenter;

/**
 * Created by Alena Drobot
 */
public class Model {

    private final static String SPACE = "space";
    private final static String DEBATES = "debates";

    private final Presenter presenter;

    public Model(Presenter presenter) {
        this.presenter = presenter;
    }

    @DrawableRes
    public int getPictureId(String name){
        // TODO
        return 0;
    }
}
