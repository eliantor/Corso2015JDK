package me.aktor.quicknote.app;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import me.aktor.quicknote.data.Contract;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/27/15.
 */
public class AddNoteTaskAsync extends AsyncTask<Note,Void,Uri> {

    private ContentResolver resolver;


    public interface OnWorkStatusChangedListener {
        public void onChangeState(String state);
    }

    private OnWorkStatusChangedListener listener;


    public static AddNoteTaskAsync start(Context context,Note note,
                                         OnWorkStatusChangedListener listener){
        Context app = context.getApplicationContext();
        AddNoteTaskAsync asy = new AddNoteTaskAsync(app.getContentResolver(),listener);
        asy.execute(note);
        return asy;
    }

    private AddNoteTaskAsync(ContentResolver resolver,OnWorkStatusChangedListener listener){
        this.resolver=  resolver;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onChangeState("Start work");
    }

    @Override
    protected Uri doInBackground(Note... params) {
        Note note = params[0];
        ContentValues values = new ContentValues();
        values.put(Contract.Note.TITLE,note.title);
        values.put(Contract.Note.CONTENT,note.content);
        values.put(Contract.Note.DATE,note.getDate());
        values.put(Contract.Note.FAVOURITE,note.favourite);

//        for (int i = 0; i < 1000; i++) {
//            longOp();
//            if (Thread.currentThread().isInterrupted()){
//                Thread.currentThread().interrupt();
//            }
//        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {

        }
        Uri uri = resolver.insert(Contract.Note.CONTENT_URI, values);
        return uri;
    }

    private void longOp() {

    }

    @Override
    protected void onPostExecute(Uri uri) {
        super.onPostExecute(uri);
        listener.onChangeState("Work done "+uri);
    }
}
