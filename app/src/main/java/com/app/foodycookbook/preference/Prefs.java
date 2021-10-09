package com.app.foodycookbook.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashSet;
import java.util.Map;

/**
 * Handle the Shared preference of the application.
 *
 * @author umesh
 * @Description this class is used to set preferences value in the ONOTE_PREF file  just like user name ,password phonenumber etc.
 */
public class Prefs {
    private static Prefs instance;
    private static final String PREF_NAME = "dovesnet";

    public static Prefs getInstance() {
        if (instance == null) {
            instance = new Prefs();
        }
        return instance;
    }

    public static void resetPreference(Context context) {
        SharedPreferences info = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.clear();
        editor.commit();
    }

    public static void logout(Context context,String Key) {
        SharedPreferences info = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.remove(Key);
        editor.commit();
    }
    public static void timerClear(Context context,String Key) {
        SharedPreferences info = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.remove(Key);
        editor.commit();
    }
    public static void clearChannelUri(Context context,String Key) {
        SharedPreferences info = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.remove(Key);
        editor.commit();
    }

    public static void resetPreferenceConditional(Context context, String keyStillRemain) {
        SharedPreferences info = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Editor editor = info.edit();

        Map<String, ?> prefs = info.getAll();
        for (Map.Entry<String, ?> prefToReset : prefs.entrySet()) {
            if (!prefToReset.getKey().equals(keyStillRemain))
                editor.remove(prefToReset.getKey()).commit();
        }
    }


    /**
     * Save the string into the shared preference.
     *
     * @param context Context object.
     * @param key     Key to save.
     * @param value   String value associated with the key.
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Get the string value of key from shared preference.
     *
     * @param key      Key whose value need to be searched.
     * @param defValue Default value to return in case no such key exist.
     * @return Value associated with the key.
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences info = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return info.getString(key, defValue);
    }

    public static void saveStringSet(Context context, String key, HashSet<String> cookies) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.putStringSet(key, cookies);
        editor.commit();
    }

    public static HashSet<String> getStringSet(Context context, String key, HashSet<String> defValue) {
        SharedPreferences info = context.getApplicationContext()
                .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return ((HashSet<String>) info.getStringSet(key, defValue));
    }
    /**
     * Save the boolean into the shared preference.
     *
     * @param context Context object.
     * @param key     Key to save.
     * @param value   String value associated with the key.
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }





    /**
     * Get the boolean value of key from shared preference.
     *
     * @param key      Key whose value need to be searched.
     * @param defValue Default value to return in case no such key exist.
     * @return Value associated with the key.
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return info.getBoolean(key, defValue);
    }

    /**
     * Save the Integer into the shared preference.
     *
     * @param context Context object.
     * @param key     Key to save.
     * @param value   Integer value associated with the key.
     */
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Get the Integer value of key from shared preference.
     *
     * @param key      Key whose value need to be searched.
     * @param defValue Default value to return in case no such key exist.
     * @return Value associated with the key.
     */
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return info.getInt(key, defValue);
    }


    public static void saveLong(Context context, String key, long value) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    public static long getLong(Context context, String key, long defValue) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return info.getLong(key, defValue);
    }


    public static void saveDouble(Context context, String key, double value) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        Editor editor = info.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.commit();
    }


    public static double getDouble(Context context, String key, double defValue) {
        SharedPreferences info = context.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return Double.longBitsToDouble(info.getLong(key, Double.doubleToLongBits(defValue)));
    }


    public static void savePreference(SharedPreferences prefs, String key,
                                      boolean value) {
        Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setRegistrationPrefs(SharedPreferences prefs,
                                            boolean value) {
        Prefs.savePreference(prefs, "REGISTRATION_CHECK", value);
    }

    public static boolean getRegistrationPrefs(SharedPreferences prefs) {
        return prefs.getBoolean("REGISTRATION_CHECK", false);
    }

}
