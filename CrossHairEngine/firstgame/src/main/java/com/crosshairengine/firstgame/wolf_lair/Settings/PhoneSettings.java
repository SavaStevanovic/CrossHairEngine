package com.crosshairengine.firstgame.wolf_lair.Settings;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import static java.security.AccessController.getContext;

/**
 * Created by Sava on 5/2/2017.
 */

public class PhoneSettings {
    private int height;
    private int width;

    private static final PhoneSettings ourInstance = new PhoneSettings();

    public static PhoneSettings getInstance() {
        return ourInstance;
    }

    private PhoneSettings() {
        width = Resources.getSystem().getDisplayMetrics().widthPixels;
        height = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return width;
    }
}
