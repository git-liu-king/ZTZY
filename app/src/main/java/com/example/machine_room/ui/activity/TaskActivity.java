package com.example.machine_room.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.frame.base.BaseActivity;
import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;
import com.example.machine_room.adapter.VpAdapter;
import com.example.machine_room.config.IntentConfig;
import com.example.machine_room.config.SpConfig;
import com.example.machine_room.ui.fragment.NotTaskFragment;
import com.example.machine_room.ui.fragment.SentTaskFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 刘博 on 2020/7/13
 */
public class TaskActivity extends BaseActivity<MainModel> implements IMainView, View.OnClickListener {

    @BindView(R.id.mToolBar_title_text)
    TextView mToolBarTitleText;
    @BindView(R.id.mToolBar_left_image)
    ImageView mToolBarLeftImage;
    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mVp)
    ViewPager mVp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mToolBarLeftImage.setImageResource(R.mipmap.ic_arrow_back);
        mToolBarTitleText.setText(R.string.task);

        String localRoomName = getIntent().getStringExtra(IntentConfig.DEVICE_ROOM_NAME);

        int[] localTitles = {R.string.task_not_text,R.string.task_sent_text};
        ArrayList<BaseFragment> localFragments = new ArrayList<>();
        localFragments.add(NotTaskFragment.getInstance(localRoomName));
        localFragments.add(SentTaskFragment.getInstance());
        VpAdapter localAdapter = new VpAdapter(getSupportFragmentManager(), localFragments, localTitles);
        mVp.setAdapter(localAdapter);
        mTab.setupWithViewPager(mVp);
    }

    @Override
    public void initListener() {
        mToolBarLeftImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mToolBar_left_image:
                finish();
                break;
        }
    }
}
