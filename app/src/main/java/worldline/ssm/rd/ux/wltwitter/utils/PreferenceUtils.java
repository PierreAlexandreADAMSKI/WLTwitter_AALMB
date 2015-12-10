package worldline.ssm.rd.ux.wltwitter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import worldline.ssm.rd.ux.wltwitter.WLTwitterApplication;

/**
 * Created by isen on 03/12/2015.
 */
public class PreferenceUtils {

    private static SharedPreferences getSharedPreferences(Context context){
      return context.getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }
    private static SharedPreferences getSharedPreferences(){
      return WLTwitterApplication.getContext().getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }
    public static String getLogin(){
        final SharedPreferences prefs = getSharedPreferences();
        return prefs.getString(Constants.Preferences.PREF_LOGIN, null);
    }
    public static void setLogin(String login){
        final SharedPreferences prefs = getSharedPreferences();
        prefs.edit().putString(Constants.Preferences.PREF_LOGIN, login).commit();
    }
    public static String getPassord(){
        final SharedPreferences prefs = getSharedPreferences();
        return prefs.getString(Constants.Preferences.PREF_PASSWORD,null);
    }
    public static void setPassword(String password){
        final SharedPreferences prefs = getSharedPreferences();
        prefs.edit().putString(Constants.Preferences.PREF_PASSWORD,password).commit();
    }
}
