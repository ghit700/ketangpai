package com.ketangpai.viewInterface;

import java.io.File;

/**
 * Created by nan on 2016/4/24.
 */
public interface DataViewInterface {
    void onProgress(int value,long total);

    void downloadOnComplete(File file);
}
