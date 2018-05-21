package nsu.fit.questapp.view.gallary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nsu.fit.questapp.R;

import static nsu.fit.questapp.utils.StringUtils.isEmpty;

/**
 * Created by Alena Drobot
 */

public class GalleryCardFragment extends Fragment {

    public final static String DESCRIPTION = "description";
    public final static String BUTTON_TEXT = "button";
    public final static String TYPE = "type";
    public final static String SPACE = "space";
    public final static String DEBATES = "debates";
    public final static String CUSTOM = "custom";

    private GalleryFragmentListener fragmentListener;
    private TextView descriptionView;
    private Button actionButton;
    private ImageView cardLogo;
    private String errorMessage;
    private String description;
    private String buttonText;
    private String cardType;

    public interface GalleryFragmentListener {  //для общения с активити
        void showError(String errorMessage);
        void openQuest(String type);
        void openFileBrowser();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentListener = (GalleryFragmentListener) getActivity();
        } catch (ClassCastException error) {
            throw new ClassCastException(getActivity().toString() + "must implement GalleryCardFragment");
        }
        errorMessage = getString(R.string.gallery_activity_bundle_error);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_card, container, false);
        try {
            initFields(getArguments());
        } catch (Exception error) {
            fragmentListener.showError(error.getMessage());
        }
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        initLogo(view);
        initDescription(view);
        initActionButton(view);
    }

    private void initFields(Bundle extras) throws Exception{
        if (isBundleEmpty(extras)) {
            throw new Exception(errorMessage);
        }
        description = extras.getString(DESCRIPTION);
        buttonText = extras.getString(BUTTON_TEXT);
        cardType = extras.getString(TYPE);
        checkFields();
    }

    private void checkFields() throws Exception{
        if (isEmpty(description) || isEmpty(buttonText) || isEmpty(cardType)) {
            throw new Exception(errorMessage);
        }
    }

    private void initLogo(View view) {
        cardLogo = view.findViewById(R.id.card_logo);
        cardLogo.setImageResource(getLogoResource());
    }

    private void initDescription(View view) {
        descriptionView = view.findViewById(R.id.card_description);
        descriptionView.setText(description);
    }

    private void initActionButton(View view) {
        actionButton = view.findViewById(R.id.card_action_button);
        actionButton.setText(buttonText);
        actionButton.setOnClickListener(v -> {
            if (cardType.equals(CUSTOM)) {
                fragmentListener.openFileBrowser();
            } else {
                fragmentListener.openQuest(cardType);
            }
        });
    }

    private boolean isBundleEmpty(Bundle bundle) {
        return bundle == null || bundle.isEmpty();
    }

    @DrawableRes
    private int getLogoResource() {  //в зависимости от типа карточки вернуть id логотипа
        switch (cardType) {
            case SPACE:
                return R.drawable.ic_space_logo;
            case DEBATES:
                return R.drawable.ic_debates_logo;
            case CUSTOM:
                return R.drawable.ic_custom_logo;
            default:
                return R.drawable.ic_custom_logo;
        }
    }
}
