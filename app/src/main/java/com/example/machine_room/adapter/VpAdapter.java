package com.example.machine_room.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.frame.base.BaseApp;
import com.example.frame.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 刘博 on 2020/7/16
 */
public class VpAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> mList;
    private int[] mTitles;

    public VpAdapter(@NonNull FragmentManager fm, ArrayList<BaseFragment> pList, int[] pTitles) {
        super(fm);
        mList = pList;
        mTitles = pTitles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return BaseApp.getRes().getString(mTitles[position]);
    }
}
