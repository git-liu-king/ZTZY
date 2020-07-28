package com.example.machine_room.ui.fragment;

import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;
import com.example.machine_room.adapter.VpAdapter;
import com.example.machine_room.design.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/7/10
 */
public class MessageFragment extends BaseFragment<MainModel> implements IMainView {

    @BindView(R.id.mToolBar_title_text)
    TextView mToolBarTitleText;
    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mVp)
    NoScrollViewPager mVp;

    public static MessageFragment getInstance() {
        MessageFragment localFragment = new MessageFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mToolBarTitleText.setText(R.string.message);

        int[] mTitles = {R.string.message_not_read,R.string.message_sent_read};
        ArrayList<BaseFragment> localFragments = new ArrayList<>();
        localFragments.add(NotReadFragment.getInstance());
        localFragments.add(SentReadFragment.getInstance());
        VpAdapter localAdapter = new VpAdapter(getChildFragmentManager(), localFragments, mTitles);
        mVp.setAdapter(localAdapter);
        mTab.setupWithViewPager(mVp);

    }
}
