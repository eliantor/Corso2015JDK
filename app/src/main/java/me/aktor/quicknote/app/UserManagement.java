package me.aktor.quicknote.app;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrea Tortorella on 7/4/15.
 */
public class UserManagement {

    private static final String USER_DATA = "user_data";
    private static final String TOKEN = "token";
    private SharedPreferences mPrefs;

    public UserManagement(Context context){
        mPrefs = context.getSharedPreferences(USER_DATA,Context.MODE_PRIVATE);
    }

    public void saveTokenFromResponse(JSONObject obj){
        String session = null;
        try {
            session = obj.getJSONObject("data").getJSONObject("user").getString("X-BB-SESSION");
        } catch (JSONException e) {
            session = null;
        }

        saveToken(session);
    }
    public void saveToken(String token){
        mPrefs.edit().putString(TOKEN,token).apply();
    }

    public String getToken(){
        return mPrefs.getString(TOKEN,null);
    }
}
