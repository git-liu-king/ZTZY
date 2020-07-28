package com.example.machine_room.ui.fragment;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 刘博 on 2020/7/10
 */
public class MineFragment extends BaseFragment<MainModel> implements IMainView {

    @BindView(R.id.mToolBar_title_text)
    TextView mToolBarTitleText;
    @BindView(R.id.mHead_image)
    CircleImageView mHeadImage;
    @BindView(R.id.mName_text)
    TextView mNameText;
    @BindView(R.id.mPhone_text)
    TextView mPhoneText;
    @BindView(R.id.mLinear_info)
    RelativeLayout mLinearInfo;
    @BindView(R.id.mLinear_alarm)
    LinearLayout mLinearAlarm;
    @BindView(R.id.mLinear_about)
    LinearLayout mLinearAbout;
    @BindView(R.id.mLinear_setting)
    LinearLayout mLinearSetting;
    @BindView(R.id.mVersion_text)
    TextView mVersionText;

    public static MineFragment getInstance() {
        MineFragment localFragment = new MineFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        mToolBarTitleText.setText(R.string.mine);
    }
}
