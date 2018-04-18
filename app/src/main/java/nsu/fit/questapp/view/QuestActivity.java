package nsu.fit.questapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nsu.fit.questapp.R;
import nsu.fit.questapp.view.fragment.QuestFragment;

/**
 * Created by Alena Drobot
 */
public class QuestActivity extends AppCompatActivity implements QuestView {

    private BottomSheetDialog sandwichMenu;
    private Button sandwichButton;
    private View restartButton;
    private View selectButton;
    private View exitButton;

    public static void start(Context context) {
        context.startActivity(new Intent(context, QuestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        initSandwichMenu();
        initViews();

        // TODO: replace to method
        QuestFragment questFragment = new QuestFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.quest_fragment, questFragment)
                .commit();
    }

    private void initViews() {
        sandwichButton = findViewById(R.id.quest_sandwich);
        setListeners();
    }

    private void setListeners() {
        sandwichButton.setOnClickListener(v -> sandwichMenu.show());
    }

    private void initSandwichMenu() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_menu, null);
        sandwichMenu = new BottomSheetDialog(this);
        sandwichMenu.setContentView(view);
        initSandwichItems(view);
    }

    private void initSandwichItems(View view) {
        restartButton = view.findViewById(R.id.quest_sandwich_restart);
        selectButton = view.findViewById(R.id.quest_sandwich_select);
        exitButton = view.findViewById(R.id.quest_sandwich_exit);
        // TODO: set listeners
    }
}
