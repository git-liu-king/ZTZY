package com.example.machine_room.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.example.frame.base.BaseApp;

/**
 * Created by 任小龙 on 2019/12/12.
 */
public class GlideUtil {

    public static void loadCircleImage(String imagePath,ImageView imageView){
        Glide.with(BaseApp.getContext())
                .load(imagePath)
                        .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadCircleImage(int imagePath,ImageView imageView){
        Glide.with(BaseApp.getContext())
                .load(imagePath)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void loadImage(String filePath,ImageView imageView) {
        ColorDrawable drawable = new ColorDrawable(Color.GRAY);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(drawable);
        Glide.with(BaseApp.getContext())
                .load(filePath)
                .apply(options)
                .into(imageView);
    }

    public static void loadImage(int imagePath,ImageView imageView) {
        ColorDrawable drawable = new ColorDrawable(Color.GRAY);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(drawable);
        Glide.with(BaseApp.getContext())
                .load(imagePath)
                .apply(options)
                .into(imageView);
    }

    public static void loadImageWithSize(Context context, int resizeX, int resizeY, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY))
                .into(imageView);
    }

    public static void loadCornerImage(ImageView imageView, String filePath, RequestListener listener, float radius) {
        CornerTransform transform = new CornerTransform(BaseApp.getContext(), radius);
        ColorDrawable drawable = new ColorDrawable(Color.GRAY);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(drawable)
                .transform(transform);
        Glide.with(BaseApp.getContext())
                .load(filePath)
                .apply(options)
                .listener(listener)
                .into(imageView);
    }

    public static void loadCornerImage(ImageView imageView, int filePath, RequestListener listener, float radius) {
        CornerTransform transform = new CornerTransform(BaseApp.getContext(), radius);
        ColorDrawable drawable = new ColorDrawable(Color.GRAY);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(drawable)
                .transform(transform);
        Glide.with(BaseApp.getContext())
                .load(filePath)
                .apply(options)
                .listener(listener)
                .into(imageView);
    }
}
