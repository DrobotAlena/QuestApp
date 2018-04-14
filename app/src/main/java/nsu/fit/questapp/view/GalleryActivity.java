package nsu.fit.questapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nsu.fit.questapp.R;
import nsu.fit.questapp.view.animation.ZoomOutPageTransformer;
import nsu.fit.questapp.view.gallary.GalleryCardFragment;

import static nsu.fit.questapp.view.gallary.GalleryCardFragment.CUSTOM;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.CUSTOM_POSITION;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.DEBATES;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.DEBATES_POSITION;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.DESCRIPTION;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.BUTTON_TEXT;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.SPACE;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.SPACE_POSITION;
import static nsu.fit.questapp.view.gallary.GalleryCardFragment.TYPE;

public class GalleryActivity extends AppCompatActivity implements GalleryCardFragment.QuestFragmentListener {

    private ViewPager galleryPager;
    private PagerAdapter galleryPagerAdapter;
    private AlertDialog errorDialog;
    private View onLeftButton;
    private View onRightButton;

    public static void start(Context context) {
        context.startActivity(new Intent(context, GalleryActivity.class));
    }

    @Override
    public void showError(String errorMessage) {
        galleryPager.setVisibility(View.GONE);
        showErrorDialog(errorMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        initActivity();
    }

    private void initActivity() {
        galleryPager = findViewById(R.id.gallery_pager);
        galleryPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        galleryPager.setAdapter(galleryPagerAdapter);
        galleryPager.setPageTransformer(true, new ZoomOutPageTransformer());
        initErrorDialog();
        initArrows();
        galleryPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                invalidateArrowsVisibility();
            }

            @Override
            public void onPageSelected(int position) {
                invalidateArrowsVisibility();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                invalidateArrowsVisibility();
            }
        });
    }

    private void initErrorDialog() {
        errorDialog = new AlertDialog.Builder(this).create();
        errorDialog.setTitle(getString(R.string.gallery_activity_error_dialog_title));
        errorDialog.setCancelable(false);
        errorDialog.setButton(AlertDialog.BUTTON_NEGATIVE,
                getString(R.string.gallery_activity_error_dialog_button),
                (dialogInterface, i) -> StartActivity.start(GalleryActivity.this));
    }

    private void showErrorDialog(@NonNull String errorMessage) {
        errorDialog.setMessage(errorMessage);
        errorDialog.show();
    }

    public void setPosition(int position) {
        galleryPager.setCurrentItem(position);
    }

    private void initArrows() {
        onLeftButton = findViewById(R.id.card_on_left_button);
        onRightButton = findViewById(R.id.card_on_right_button);
        onLeftButton.setOnClickListener(v -> setPosition(toLeft()));
        onRightButton.setOnClickListener(v -> setPosition(toRight()));
    }

    private int toLeft() {
        return galleryPager.getCurrentItem() - 1;
    }

    private int toRight() {
        return galleryPager.getCurrentItem() + 1;
    }

    private void invalidateArrowsVisibility() {
        switch (galleryPager.getCurrentItem()) {
            case SPACE_POSITION:
                onLeftButton.setVisibility(View.GONE);
                onRightButton.setVisibility(View.VISIBLE);
                break;
            case DEBATES_POSITION:
                onLeftButton.setVisibility(View.VISIBLE);
                onRightButton.setVisibility(View.VISIBLE);
                break;
            case CUSTOM_POSITION:
                onLeftButton.setVisibility(View.VISIBLE);
                onRightButton.setVisibility(View.GONE);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    GalleryCardFragment space = new GalleryCardFragment();
                    space.setArguments(buildBundle(SPACE, getString(R.string.gallery_space_description), getString(R.string.gallery_space_button)));
                    return space;
                case 1:
                    GalleryCardFragment debates = new GalleryCardFragment();
                    debates.setArguments(buildBundle(DEBATES, getString(R.string.gallery_debates_description), getString(R.string.gallery_debates_button)));
                    return debates;
                case 2:
                    GalleryCardFragment custom = new GalleryCardFragment();
                    custom.setArguments(buildBundle(CUSTOM, getString(R.string.gallery_custom_description), getString(R.string.gallery_custom_button)));
                    return custom;
                default:
                    GalleryCardFragment defaultCard = new GalleryCardFragment();
                    defaultCard.setArguments(buildBundle(SPACE, getString(R.string.gallery_space_description), getString(R.string.gallery_space_button)));
                    return defaultCard;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        private Bundle buildBundle(@NonNull String type, @NonNull String description, @NonNull String buttonText) {
            Bundle bundle = new Bundle();
            bundle.putString(TYPE, type);
            bundle.putString(DESCRIPTION, description);
            bundle.putString(BUTTON_TEXT, buttonText);
            return bundle;
        }
    }
}
