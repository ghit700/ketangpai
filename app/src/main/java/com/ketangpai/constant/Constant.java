package com.ketangpai.constant;

import android.os.Environment;

import com.ketangpai.utils.AppContextUtils;

import java.io.File;

/**
 * Created by nan on 2016/4/9.
 */
public class Constant {


    public final static String MAIN_FOLDER = "ketangpai" + File.separator;
    public final static String DATA_FOLDER = "data" + File.separator;
    public static final String ALBUM_PATH = Environment.getExternalStorageDirectory() + File.separator + MAIN_FOLDER + File.separator;
    public final static String PHOTO_FOLDER = ALBUM_PATH + "photo" + File.separator;
    public final static String LOGO_FOLDER = PHOTO_FOLDER
            + AppContextUtils.getInstance().getSharedPreferences("user", 0).getString("account", "")
            + "logo.jpg";

}
