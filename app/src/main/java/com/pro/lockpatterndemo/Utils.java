package com.pro.lockpatterndemo;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.io.File;

public class Utils {

    public static String getRootPath(Context context) {
        File[] externalStorageVolumes =
                ContextCompat.getExternalFilesDirs(context, null);
        File primaryExternalStorage = externalStorageVolumes[0];
        return primaryExternalStorage.getAbsolutePath();
    }
}
