package com.example.machine_room.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.frame.base.BaseActivity;
import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;
import com.example.machine_room.push.DemoIntentService;
import com.example.machine_room.ui.fragment.NotAlarmFragment;
import com.example.machine_room.ui.fragment.SentAlarmFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import javax.crypto.Mac;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/7/13
 */
public class AlarmActivity extends BaseActivity<MainModel> implements IMainView, TabLayout.BaseOnTabSelectedListener, View.OnClickListener {

    private static final int NOT_FRAGMENT = 0;
    private static final int SENT_FRAGMENT = 1;

    @BindView(R.id.mToolBar_title_text)
    TextView mToolBarTitleText;
    @BindView(R.id.mToolBar_left_image)
    ImageView mToolBarLeftImage;
    @BindView(R.id.mTab)
    TabLayout mTab;
    private FragmentManager mManager;
    private ArrayList<BaseFragment> mFragments;
    private int mHidePosition;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_alarm;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mToolBarLeftImage.setImageResource(R.mipmap.ic_arrow_back);
        mToolBarTitleText.setText(R.string.alarm);

        mTab.addTab(mTab.newTab().setText(R.string.alarm_not_alarm));
        mTab.addTab(mTab.newTab().setText(R.string.alarm_sent_alarm));

        mFragments = new ArrayList<>();
        mFragments.add(NotAlarmFragment.getInstance());
        mFragments.add(SentAlarmFragment.getInstance());

        mManager = getSupportFragmentManager();
        mManager.beginTransaction().add(R.id.mFrame,mFragments.get(0)).commit();

        String localTabName = String.valueOf(mTab.newTab().getText());
        Intent localIntent = new Intent(this, DemoIntentService.class);
        localIntent.putExtra("cid",1);
        localIntent.putExtra("name",localTabName);
        startService(localIntent);

    }

    @Override
    public void initListener() {
        mToolBarLeftImage.setOnClickListener(this);
        mTab.addOnTabSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mToolBar_left_image:
                finish();
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab pTab) {
        int localPosition = pTab.getPosition();
        switch (localPosition){
            case NOT_FRAGMENT:
                switchFragment(NOT_FRAGMENT);
                break;
            case SENT_FRAGMENT:
                switchFragment(SENT_FRAGMENT);
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
