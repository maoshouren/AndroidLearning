package com.example.asus.a_1;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;


public class FragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    public Fragment currentFragment;

    FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object){
        currentFragment = (Fragment)object;
        super.setPrimaryItem(container, position, object);
    }
}
