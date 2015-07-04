package me.aktor.quicknote.app.sync;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import me.aktor.quicknote.app.QuickNoteApp;
import me.aktor.quicknote.app.UserManagement;
import me.aktor.quicknote.app.services.network.WebServices;

/**
 * Created by Andrea Tortorella on 7/4/15.
 */
public class SyncService extends IntentService {


    public static void start(Context context){
        Intent intent =new Intent(context,SyncService.class);
        context.startService(intent);
    }

    public SyncService() {
        super("SyncService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // essguiamo chiamata di rete.
        //
        Cursor dataToSave = obtainDataToSave();
        if (shouldSaveNow(dataToSave)) {
            saveDataAndAccount(dataToSave);
        }

    }

    private boolean shouldSaveNow(Cursor dataToSave){
      return true;
    }
    private Cursor obtainDataToSave() {
        return null;
    }

    private void saveDataAndAccount(Cursor cursor){

        List<JSONObject> objects = convertToRequests(cursor);
        UserManagement management = QuickNoteApp.get().getUserManager();

        for (JSONObject o:objects) {

            try {
                JSONObject response = WebServices.createDocument(management.getToken(),
                        o.getString("title"), o.getString("content"));

                updateDocumentLastSync();
            } catch (JSONException e) {

            }

        }
        // per ogni nota non salvata
        // salvarla sul server e registrare l'evento in locale
    }

    private void updateDocumentLastSync(/*which document*/) {

    }

    private List<JSONObject> convertToRequests(Cursor cursor) {
        return null;
    }
}

