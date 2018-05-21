package nsu.fit.questapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import nsu.fit.questapp.presenter.Presenter;
import nsu.fit.questapp.presenter.QuestPresenter;
import nsu.fit.questapp.utils.DrawableUtils;
import nsu.fit.questapp.utils.StringUtils;

import static nsu.fit.questapp.view.gallary.GalleryCardFragment.CUSTOM;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.SPACE;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.TYPE;

/**
 * Created by Alena Drobot
 */
public class QuestActivity extends AppCompatActivity implements QuestView {

    final static String JSON_URI = "uri";

    private final static String QUEST_STATUS = "finish";
    private final static int EMPTY_DRAWABLE_NAME = 0; //возвращает ошибку
    private final static int INITIAL_PAGE = 0; //с какой страницы начинаем
    private final static int FIRST_BUTTON_ID = 0;
    private final static int BUTTONS_SIZE = 4;

    private Presenter presenter;
    private int cardId = INITIAL_PAGE;
    private String cardType;
    private String jsonUri;

    @Nullable
    private CardData card; //одна карта которая отрисовывается

    /**
     * Sandwich Menu
     */
    private Button sandwichButton;
    private BottomSheetDialog sandwichMenu;
    private View restartSandwichButton;
    private View selectSandwichButton;
    private View exitSandwichButton;

    /**
     * Views
     */
    private TextView title;
    private ImageView picture;
    private TextView description;
    private LinearLayout buttonsLayout; //4 кнопки выбора
    private LinearLayout resultButtonsLayout; //выйти начать заново... финишные кнопки
    private ArrayList<Button> buttons;

    /**
     * Result Buttons
     */
    private Button restartButton;
    private Button selectButton;
    private Button exitButton;

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

    private void invalidate() { //отрисовка
        initCard();
        initPicture();
        initDescription();
        initButtons();
    }

    private void initCard() {
        String textTitle;
        title = findViewById(R.id.quest_title);
        setCardTypeFromBundle();
        if (cardType.equals(CUSTOM) ) {
            setJsonUriFromBundle();
            if(!StringUtils.isEmpty(jsonUri)) {
                card = presenter.getCardFromStorage(Uri.parse(jsonUri), cardId);
                textTitle = presenter.getQuestTitleFromStorage(Uri.parse(jsonUri));
                if(!StringUtils.isEmpty(textTitle)) {
                    title.setText(textTitle);
                }
            }
        } else {
            card = presenter.getCard(cardType, cardId);
            textTitle = presenter.getQuestTitle(cardType);
            if(!StringUtils.isEmpty(textTitle)) {
                title.setText(textTitle);
            }
        }
    }

    private void setCardTypeFromBundle() { //по дефолту всегда игра космос. Иначе по TYPE
        if (StringUtils.isEmpty(cardType)) {
            if (isBundleEmpty(getIntent().getExtras())) {
                cardType = SPACE;
            } else {
                cardType = getIntent().getStringExtra(TYPE);
            }
        }
    }

    private void setJsonUriFromBundle() {
        if (StringUtils.isEmpty(jsonUri)) {
            if (isBundleEmpty(getIntent().getExtras())) {
                jsonUri = null;
            } else {
                jsonUri = getIntent().getStringExtra(JSON_URI);
            }
        }
    }

    private void initPicture() {
        picture = findViewById(R.id.quest_picture);
        if (card != null && DrawableUtils.getDrawableId(this, card.getDrawableName()) != EMPTY_DRAWABLE_NAME) {
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
        resultButtonsLayout = findViewById(R.id.result_buttons_set);
        initQuestButtonsSet(buttonsLayout);
        initResultButtons(resultButtonsLayout);
        if (card != null && StringUtils.isEmpty(card.getStatus())) { //обычная карточка
            resultButtonsLayout.setVisibility(View.GONE);
            ArrayList<ButtonData> buttonData = card.getButtons();
            if (buttonData == null || buttonData.size() != BUTTONS_SIZE) {
                buttonsLayout.setVisibility(View.GONE);
            } else {
                buttonsLayout.setVisibility(View.VISIBLE);
                for (int buttonId = FIRST_BUTTON_ID; buttonId < BUTTONS_SIZE; buttonId++) {
                    final String description = buttonData.get(buttonId).getText();
                    final int referenceTo = buttonData.get(buttonId).getReference();
                    buttons.get(buttonId).setText(description);
                    buttons.get(buttonId).setOnClickListener(view -> {
                        cardId = referenceTo;
                        invalidate();
                    });
                }
            }
        } else if (card != null && card.getStatus().equals(QUEST_STATUS)){ //если конец
            buttonsLayout.setVisibility(View.GONE);
            resultButtonsLayout.setVisibility(View.VISIBLE);
        } else {
            buttonsLayout.setVisibility(View.GONE);
            resultButtonsLayout.setVisibility(View.GONE);
        }
    }

    private void initQuestButtonsSet(View view) {
        buttons = new ArrayList<>();
        buttons.add(view.findViewById(R.id.quest_first_action));
        buttons.add(view.findViewById(R.id.quest_second_action));
        buttons.add(view.findViewById(R.id.quest_third_action));
        buttons.add(view.findViewById(R.id.quest_fourth_action));
    }

    private void initResultButtons(View view) {
        restartButton = view.findViewById(R.id.result_restart_button);
        restartButton.setOnClickListener(v -> recreate());
        selectButton = view.findViewById(R.id.result_select_button);
        selectButton.setOnClickListener(v -> GalleryActivity.start(this));
        exitButton = view.findViewById(R.id.result_exit_button);
        exitButton.setOnClickListener(v -> {
            StartActivity.start(this);
            finish();
        });
    }

    private void setListeners() {
        sandwichButton.setOnClickListener(v -> sandwichMenu.show());
    }

    private void initSandwichMenu() {
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_menu, null);
        sandwichMenu = new BottomSheetDialog(this); //bottom... - фрагмент
        sandwichMenu.setContentView(view);
        initSandwichItems(view);
    }

    private void initSandwichItems(View view) {
        restartSandwichButton = view.findViewById(R.id.quest_sandwich_restart);
        restartSandwichButton.setOnClickListener(v -> {
            sandwichMenu.hide();
            recreate();
        });

        selectSandwichButton = view.findViewById(R.id.quest_sandwich_select);
        selectSandwichButton.setOnClickListener(v -> {
            sandwichMenu.hide();
            GalleryActivity.start(this);
        });

        exitSandwichButton = view.findViewById(R.id.quest_sandwich_exit);
        exitSandwichButton.setOnClickListener(v -> {
            StartActivity.start(this);
            finish();
        });
    }

    @Override
    public void showError(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean isBundleEmpty(@Nullable Bundle bundle) {
        return bundle == null || bundle.isEmpty();
    }
}
