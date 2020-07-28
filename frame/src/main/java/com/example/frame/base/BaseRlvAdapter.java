package com.example.frame.base;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRlvAdapter extends RecyclerView.Adapter {

    public OnItemClikListener mItemClikListener;
    public OnItemLongClikListener mItemLongClikListener;

    public interface OnItemClikListener{
        void onItemClik(int position);
    }

    public void setmItemClikListener(OnItemClikListener mItemClikListener) {
        this.mItemClikListener = mItemClikListener;
    }


    public interface OnItemLongClikListener{
        void onItemLongClik(int position);
    }

    public void setmItemLongClikListener(OnItemLongClikListener mItemLongClikListener) {
        this.mItemLongClikListener = mItemLongClikListener;
    }
}
