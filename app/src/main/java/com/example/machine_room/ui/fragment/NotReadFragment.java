package com.example.machine_room.ui.fragment;

import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.frame.base.BaseFragment;
import com.example.frame.base.IMainView;
import com.example.frame.base.MainModel;
import com.example.machine_room.R;

import butterknife.BindView;

/**
 * Created by 刘博 on 2020/7/23
 */
public class NotReadFragment extends BaseFragment<MainModel> implements IMainView {

    @BindView(R.id.mWeb)
    WebView mWeb;

    /*@BindView(R.id.mNot_read)
    TextView mNotRead;
    @BindView(R.id.mRlv)
    RecyclerView mRlv;
    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;*/

    public static NotReadFragment getInstance() {
        NotReadFragment localFragment = new NotReadFragment();
        return localFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_not_read;
    }

    @Override
    protected MainModel initModel() {
        return new MainModel();
    }

    @Override
    protected void initView() {
        //mRefreshLayout.setEnableRefresh(false);
        //mRefreshLayout.setEnableLoadMore(false);

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        WebSettings localWebSettings = mWeb.getSettings();
        localWebSettings.setJavaScriptEnabled(true);
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setLoadWithOverviewMode(true);
        localWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        localWebSettings.setSupportZoom(true);  //支持放大缩小
        localWebSettings.setBuiltInZoomControls(true); //显示缩放按钮
        //localWebSettings.setTextSize(100);
        mWeb.loadUrl("https://jgraph.github.io/mxgraph/javascript/examples/monitor.html");
        mWeb.setWebChromeClient(new WebChromeClient());
        mWeb.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mWeb.setInitialScale(125);

    }
}
