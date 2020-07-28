package com.example.machine_room.ui.activity;

import android.widget.FrameLayout;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.frame.base.BaseActivity;
import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.IMianModel;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;
import com.example.machine_room.ui.fragment.HomeFragment;
import com.example.machine_room.ui.fragment.MessageFragment;
import com.example.machine_room.ui.fragment.MineFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/6/22
 */
public class MianActivity extends BaseActivity implements IMainView, TabLayout.BaseOnTabSelectedListener {

    private final int HOME_FRAGMENT = 0;
    private final int MESSAGE_FRAGMENT = 1;
    private final int MINE_FRAGMENT = 2;

    @BindView(R.id.mFrame)
    FrameLayout mFrame;
    @BindView(R.id.mTab)
    TabLayout mTab;
    private ArrayList<BaseFragment> mFragments;
    private FragmentManager mManager;
    private int mHidePosition;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IMianModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mTab.addTab(mTab.newTab().setText(R.string.home));
        mTab.addTab(mTab.newTab().setText(R.string.message));
        mTab.addTab(mTab.newTab().setText(R.string.mine));

        mFragments = new ArrayList<>();
        mFragments.add(HomeFragment.getInstance());
        mFragments.add(MessageFragment.getInstance());
        mFragments.add(MineFragment.getInstance());

        mManager = getSupportFragmentManager();
        mManager.beginTransaction().add(R.id.mFrame,mFragments.get(0)).commit();

    }

    @Override
    public void initListener() {
        mTab.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab pTab) {
        int localPosition = pTab.getPosition();
        switch (localPosition){
            case HOME_FRAGMENT:
                switchFragment(HOME_FRAGMENT);
                break;
            case MESSAGE_FRAGMENT:
                switchFragment(MESSAGE_FRAGMENT);
                break;
            case MINE_FRAGMENT:
                switchFragment(MINE_FRAGMENT);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab pTab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab pTab) {

    }

    private void switchFragment(int pType) {
        FragmentTransaction localTransaction = mManager.beginTransaction();
        BaseFragment showFragment = mFragments.get(pType);
        if (!showFragment.isAdded()) {
            localTransaction.add(R.id.mFrame, showFragment);
        }
        BaseFragment hideFragment = mFragments.get(mHidePosition);
        localTransaction.hide(hideFragment);
        localTransaction.show(showFragment);
        localTransaction.commit();
        mHidePosition = pType;
    }
}
