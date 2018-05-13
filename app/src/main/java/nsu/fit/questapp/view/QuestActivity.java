package nsu.fit.questapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nsu.fit.questapp.R;
import nsu.fit.questapp.model.card.ButtonData;
import nsu.fit.questapp.model.card.CardData;
import nsu.fit.questapp.presenter.QuestPresenter;
import nsu.fit.questapp.utils.DrawableUtils;
import nsu.fit.questapp.utils.StringUtils;

import static nsu.fit.questapp.view.gallary.GalleryCardFragment.CUSTOM;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.TYPE;

/**
 * Created by Alena Drobot
 */
public class QuestActivity extends AppCompatActivity implements QuestView {

    private final static int INITIAL_PAGE = 0;
    private final static int BUTTONS_SIZE = 4;

    private QuestPresenter presenter;
    private int cardId = INITIAL_PAGE;
    private String cardType;
    @Nullable
    private CardData card;

    /**
     * Sandwich Menu
     */
    private Button sandwichButton;
    private BottomSheetDialog sandwichMenu;
    private View restartButton;
    private View selectButton;
    private View exitButton;

    /**
     * Views
     */
    private ImageView picture;
    private TextView description;
    private LinearLayout buttonsLayout;
    private ArrayList<Button> buttons;

    public static void start(Context context) {
        context.startActivity(new Intent(context, QuestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);

        presenter = new QuestPresenter(this, this);

        initSandwichMenu();
        initViews();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initViews() {
        sandwichButton = findViewById(R.id.quest_sandwich);
        invalidate();
        setListeners();
    }

    private void invalidate() {
        initCard();
        initPicture();
        initDescription();
        initButtons();
    }

    private void initCard() {
        setCardType();
        card = presenter.getCard(cardType, cardId);
    }

    private void setCardType() {
        if (StringUtils.isEmpty(cardType)) {
            if (isBundleEmpty(getIntent().getExtras())) {
                cardType = CUSTOM;
            } else {
                cardType = getIntent().getStringExtra(TYPE);
            }
        }
    }

    private void initPicture() {
        picture = findViewById(R.id.quest_picture);
        if (card != null && DrawableUtils.getDrawableId(this, card.getDrawableName()) != 0) {
            picture.setImageResource(DrawableUtils.getDrawableId(this, card.getDrawableName()));
        }
    }

    private void initDescription() {
        description = findViewById(R.id.quest_description);
        if (card != null) {
            description.setText(card.getDescription());
        }
    }

    private void initButtons() {
        buttonsLayout = findViewById(R.id.quest_buttons_set);
        buttons = new ArrayList<>();
        buttons.add(buttonsLayout.findViewById(R.id.quest_first_action));
        buttons.add(buttonsLayout.findViewById(R.id.quest_second_action));
        buttons.add(buttonsLayout.findViewById(R.id.quest_third_action));
        buttons.add(buttonsLayout.findViewById(R.id.quest_fourth_action));
        if (card != null) {
            ArrayList<ButtonData> buttonData = card.getButtons();
            if (buttonData == null || buttonData.size() != BUTTONS_SIZE) {
                buttonsLayout.setVisibility(View.GONE);
            } else {
                for (int buttonId = 0; buttonId < BUTTONS_SIZE; buttonId++) {
                    final String description = buttonData.get(buttonId).getText();
                    final int referenceTo = buttonData.get(buttonId).getReference();
                    buttons.get(buttonId).setText(description);
                    buttons.get(buttonId).setOnClickListener(view -> {
                        cardId = referenceTo;
                        invalidate();
                    });
                }
            }
        }
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
        restartButton.setOnClickListener(v -> {
            recreate();
            sandwichMenu.hide();
        });

        selectButton = view.findViewById(R.id.quest_sandwich_select);
        selectButton.setOnClickListener(v -> {
            GalleryActivity.start(this);
            sandwichMenu.hide();
        });

        exitButton = view.findViewById(R.id.quest_sandwich_exit);
        exitButton.setOnClickListener(v -> {
            StartActivity.start(this);
            finish();
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isBundleEmpty(Bundle bundle) {
        return bundle == null || bundle.isEmpty();
    }
}
