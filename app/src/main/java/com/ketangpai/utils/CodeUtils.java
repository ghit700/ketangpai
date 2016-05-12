package com.ketangpai.utils;

/**
 * Created by nan on 2016/4/21.
 */
public class CodeUtils {
    public static String createCode() {
        int code_long = (int) (1000 + Math.random() * (1000000 - 1));
        return String.valueOf(code_long);
    }
}
