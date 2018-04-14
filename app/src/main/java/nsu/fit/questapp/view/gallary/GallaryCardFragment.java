package nsu.fit.questapp.view.gallary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nsu.fit.questapp.R;

/**
 * Created by Alena Drobot
 */

public class GallaryCardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery_card, container, false);
    }
}
