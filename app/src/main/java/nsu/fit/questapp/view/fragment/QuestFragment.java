package nsu.fit.questapp.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nsu.fit.questapp.R;
import nsu.fit.questapp.presenter.QuestPresenter;
import nsu.fit.questapp.view.QuestView;

/**
 * Created by Alena Drobot
 */
public class QuestFragment extends Fragment implements QuestView{

    private QuestPresenter presenter;
    private ImageView picture;
    private TextView description;
    private ArrayList<Button> buttons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quest, container, false);
        presenter = new QuestPresenter(this);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        picture = view.findViewById(R.id.quest_picture);
        picture.setImageResource(presenter.getPicture());
        description = view.findViewById(R.id.quest_description);
        buttons = new ArrayList<>();
        buttons.add(view.findViewById(R.id.quest_first_action));
        buttons.add(view.findViewById(R.id.quest_second_action));
        buttons.add(view.findViewById(R.id.quest_third_action));
        buttons.add(view.findViewById(R.id.quest_fourth_action));
    }
}
