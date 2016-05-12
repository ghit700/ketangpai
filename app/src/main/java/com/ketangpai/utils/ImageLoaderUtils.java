package com.ketangpai.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ketangpai.nan.ketangpai.R;


/**
 * Created by nan on 2016/4/16.
 */
public class ImageLoaderUtils {
    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }

        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(R.drawable.da8e974dc_r).centerCrop().diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.da8e974dc_r).crossFade().into(imageView);
    }



    public static void displayNoDisk(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }

        Glide.with(context)
                .load(url).
                placeholder(R.drawable.da8e974dc_r).
                skipMemoryCache(true).centerCrop().
                diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.da8e974dc_r).
                crossFade().
                into(imageView);
    }

    public static void displayByFileName(Context context, ImageView imageView, String url, String filename) {


        String end = filename.substring(filename.lastIndexOf(".") + 1, filename.length()).toLowerCase();
        /* 依扩展名的类型决定MimeType */
        if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") ||
                end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
            //mp3
            imageView.setImageResource(R.drawable.documents_icon_folder);

        } else if (end.equals("3gp") || end.equals("mp4")) {
            //mp4
            imageView.setImageResource(R.drawable.documents_icon_folder);

        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") ||
                end.equals("jpeg") || end.equals("bmp")) {
            display(context, imageView, url);
        } else if (end.equals("ppt")) {
            imageView.setImageResource(R.drawable.documents_icon_ppt);

        } else if (end.equals("xls")) {
            imageView.setImageResource(R.drawable.documents_icon_xls);

        } else if (end.equals("doc")||end.equals("docx")) {
            imageView.setImageResource(R.drawable.documents_icon_doc);
        } else if (end.equals("pdf")) {
            imageView.setImageResource(R.drawable.documents_icon_pdf);

        } else if (end.equals("txt")) {
            imageView.setImageResource(R.drawable.documents_icon_text);

        } else {
            imageView.setImageResource(R.drawable.documents_icon_folder);
        }
    }


}
