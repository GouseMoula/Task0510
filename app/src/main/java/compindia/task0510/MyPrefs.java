package compindia.task0510;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by compindia-fujitsu on 05-10-2017.
 */

public class MyPrefs {

    public static void setUserData(Context context, String key, String val) {
        SharedPreferences preferences = context.getSharedPreferences(MyConsts.PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(key, val).commit();

    }


    public static String getUserData(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(MyConsts.PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }


    public static void deleteAllData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(MyConsts.PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    public static void setLoggedIn(Context context, String key, boolean val) {
        SharedPreferences preferences = context.getSharedPreferences(MyConsts.PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, val).commit();
    }

    public static boolean isLoogedIn(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(MyConsts.PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);

    }

    public static void setRegistered(Context context, String key, boolean val) {
        SharedPreferences preferences = context.getSharedPreferences(MyConsts.PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, val).commit();
    }

    public static boolean isRegstered(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(MyConsts.PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);

    }
}
