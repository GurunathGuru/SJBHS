package app.integro.sjbhs.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import app.integro.sjbhs.fragments.FragmentHome;
import app.integro.sjbhs.fragments.NewsFragment;
import app.integro.sjbhs.fragments.NotificationsFragment;
import app.integro.sjbhs.fragments.WebSiteFragment;

public class SliderAdapter extends FragmentPagerAdapter {

    public SliderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new FragmentHome();
        }
        if (position == 1) {
            fragment = new NewsFragment();
        }
        if (position == 2) {
            fragment = new NotificationsFragment();
        }
        if (position == 3) {
            fragment = new WebSiteFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
