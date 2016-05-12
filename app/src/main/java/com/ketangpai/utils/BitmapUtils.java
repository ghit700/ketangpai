package com.ketangpai.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;

/**
 * Created by Administrator on 2016/4/14.
 */
public class BitmapUtils {

    /**
     * 从SD卡获取图片,根据所给的宽和高来设定输出的图片的大小
     *
     * @param bitmapPath
     * @return
     */
    public static Bitmap getBitmap(String bitmapPath, int height, int width) {

        if (null == bitmapPath) {
            return null;
        }

        Bitmap bitmap = null;

        File mfile = new File(bitmapPath);
        if (mfile.exists()) {// 若该文件存在
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize=2;
            bitmap = BitmapFactory.decodeFile(bitmapPath, options);
        }
        return bitmap;
    }

}
