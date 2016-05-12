package com.ketangpai.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

/**
 * Created by nan on 2016/4/10.
 */
public class FileUtils {
    private final static String[] imageFormat = new String[]{"jpg", "png", ".bmp", "jpeg", "gif"};
    private final static String[] videoFormat = new String[]{"mp4", "avi", "3gp", "rmvb", "mov", "wmv", "fly"};
    private final static String[] documentFormat = new String[]{"txt", "pdf", "xlsx", "doc", "ppt", "pptx", "wps", "docx", "xls"};

    public final static int TYPE_FILE_IMAGE = 0;
    public final static int TYPE_FILE_VIDEO = 1;
    public final static int TYPE_FILE_DOCUMENT = 2;

    /**
     * 获取文件的名字
     *
     * @param uri
     * @return
     */
    public static String getFileName(Uri uri) {
        String fileName = null;
        String[] path = uri.getPath().split("/");
        fileName = path[path.length - 1];
        return fileName;
    }


    /**
     * 获取文件的大小，单位为MB
     *
     * @param length
     * @return
     */
    public static String getFileSize(long length) {
        double size = length / (double) 1024 / (double) 1024;

        DecimalFormat df = new DecimalFormat("#.00");

        if (size < 1) {
            return 0 + df.format(size);
        }

        return df.format(size);

    }

    /**
     * 获取文件的类型
     *
     * @param fileName
     * @return
     */
    public static int getFileType(String fileName) {
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        for (String format : documentFormat) {
            if (format.equals(type)) {
                return TYPE_FILE_DOCUMENT;
            }

        }

        for (String format : imageFormat) {
            if (format.equals(type)) {
                return TYPE_FILE_IMAGE;
            }

        }

        for (String format : videoFormat) {
            if (format.equals(type)) {
                return TYPE_FILE_VIDEO;
            }

        }
        return -1;
    }


    /**
     * 通过Uri返回File文件
     * 注意：通过相机的是类似content://media/external/images/media/97596
     * 通过相册选择的：file:///storage/sdcard0/DCIM/Camera/IMG_20150423_161955.jpg
     * 通过查询获取实际的地址
     *
     * @param uri     Uri
     * @param context 上下文对象  Activity
     * @return File
     */
    public static File getFileByUri(Activity context, Uri uri) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA}, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以后
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        }
        return null;
    }

    public static void createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    public static void overloadFile(File sourse, File target) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(target, false);
            FileInputStream fileInputStream = new FileInputStream(sourse);

            byte[] data = new byte[128];
            while ((fileInputStream.read(data, 0, data.length)) != -1) {
                fileOutputStream.write(data);
            }


            fileOutputStream.flush();
            fileOutputStream.getFD().sync();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {

        }
    }

    ;


}
