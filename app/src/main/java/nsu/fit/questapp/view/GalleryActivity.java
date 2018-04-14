package nsu.fit.questapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import nsu.fit.questapp.R;
import nsu.fit.questapp.view.animation.ZoomOutPageTransformer;
import nsu.fit.questapp.view.gallary.GallaryCardFragment;

public class GalleryActivity extends AppCompatActivity {

    private ViewPager galleryPager;
    private PagerAdapter galleryPagerAdapter;

    private GallaryCardFragment galleryCardFragment;

    public static void start(Context context) {
        context.startActivity(new Intent(context, GalleryActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        galleryPager = findViewById(R.id.gallery_pager);
        galleryPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        galleryPager.setAdapter(galleryPagerAdapter);
        galleryPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 1:
                    galleryCardFragment = new GallaryCardFragment();
                    return galleryCardFragment;
                case 2:
                    galleryCardFragment = new GallaryCardFragment();
                    return galleryCardFragment;
                case 3:
                    galleryCardFragment = new GallaryCardFragment();
                    return galleryCardFragment;
                case 4:
                    galleryCardFragment = new GallaryCardFragment();
                    return galleryCardFragment;
                default:
                    return new GallaryCardFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
