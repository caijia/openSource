package com.caijia.analysisopensource.ebusiness.commom.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 保存本地数据帮助类
 * 方法名后缀包含temp的方法，在调用clear的时候，保存的数据将被清除，
 * 例如数据只有在登录时才有用，退出登录时清除，建议用temp为后缀的方法进行操作
 * Created by cai.jia on 2017/11/3 0003.
 */

public class PreferenceHelper {

    private static final String TEMP_DATA = "params:tempData";
    private static volatile PreferenceHelper instance;
    private SharedPreferences preferences;
    private Gson gson;

    private PreferenceHelper(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (PreferenceHelper.class) {
                if (instance == null) {
                    instance = new PreferenceHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putLong(key, value);
        edit.apply();
    }

    public void putFloat(String key, float value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putFloat(key, value);
        edit.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public <T> void putObject(String key, T t) {
        if (t == null) {
            return;
        }
        String json = gson.toJson(t);
        putString(key, json);
    }

    public void putIntTemp(String key, int value) {
        String newKey = TEMP_DATA + key;
        putInt(newKey, value);
    }

    public void putLongTemp(String key, long value) {
        String newKey = TEMP_DATA + key;
        putLong(newKey, value);
    }

    public void putFloatTemp(String key, float value) {
        String newKey = TEMP_DATA + key;
        putFloat(newKey, value);
    }

    public void putBooleanTemp(String key, boolean value) {
        String newKey = TEMP_DATA + key;
        putBoolean(newKey, value);
    }

    public void putStringTemp(String key, String value) {
        String newKey = TEMP_DATA + key;
        putString(newKey, value);
    }

    public <T> void putObjectTemp(String key, T t) {
        String newKey = TEMP_DATA + key;
        putObject(newKey, t);
    }

    public int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public @Nullable
    <T> T getObject(String key, Class<T> clazz) {
        String json = getString(key, null);
        if (!TextUtils.isEmpty(json)) {
            return gson.fromJson(json, clazz);
        }
        return null;
    }

    public int getIntTemp(String key, int defaultValue) {
        String newKey = TEMP_DATA + key;
        return preferences.getInt(newKey, defaultValue);
    }

    public long getLongTemp(String key, long defaultValue) {
        String newKey = TEMP_DATA + key;
        return preferences.getLong(newKey, defaultValue);
    }

    public float getFloatTemp(String key, float defaultValue) {
        String newKey = TEMP_DATA + key;
        return preferences.getFloat(newKey, defaultValue);
    }

    public boolean getBooleanTemp(String key, boolean defaultValue) {
        String newKey = TEMP_DATA + key;
        return preferences.getBoolean(newKey, defaultValue);
    }

    public String getStringTemp(String key, String defaultValue) {
        String newKey = TEMP_DATA + key;
        return preferences.getString(newKey, defaultValue);
    }

    public @Nullable
    <T> T getObjectTemp(String key, Class<T> clazz) {
        String newKey = TEMP_DATA + key;
        String json = getString(newKey, null);
        if (!TextUtils.isEmpty(json)) {
            return gson.fromJson(json, clazz);
        }
        return null;
    }

    public void clear() {
        Map<String, ?> map = preferences.getAll();
        List<String> clearList = new ArrayList<>();
        for (String key : map.keySet()) {
            if (key.startsWith(TEMP_DATA)) {
                clearList.add(key);
            }
        }

        for (String key : clearList) {
            SharedPreferences.Editor edit = preferences.edit();
            edit.remove(key);
            edit.apply();
        }
    }
}
