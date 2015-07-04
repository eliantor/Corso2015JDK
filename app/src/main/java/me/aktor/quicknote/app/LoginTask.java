package me.aktor.quicknote.app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import me.aktor.quicknote.app.services.network.WebServices;

/**
 * Created by Andrea Tortorella on 7/4/15.
 */
public class LoginTask extends AsyncTask<String,Void,String> {

    private static final String TAG = "Login";


    private final String username;
    private final String password;

    public LoginTask(String username,String password){
        this.username = username;
        this.password = password;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            JSONObject login = WebServices.login(username, password);
            Log.d(TAG,"Login data: "+login.toString());
            UserManagement manager = QuickNoteApp.get().getUserManager();
            manager.saveTokenFromResponse(login);

        } catch (Exception e){
            Log.e(TAG,"error during login",e);
            return null;
        }
        return null;
    }
}
