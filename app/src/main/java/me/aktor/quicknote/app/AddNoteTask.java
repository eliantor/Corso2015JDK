package me.aktor.quicknote.app;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import me.aktor.quicknote.data.Contract;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
public class AddNoteTask extends AsyncQueryHandler {
    private static final String TAG = "AddNoteTask";

    public interface OnWorkStatusChangedListener {
        public void onChangeState(String state);
    }

    private OnWorkStatusChangedListener listener;

    public static AddNoteTask newInstance(Context context,OnWorkStatusChangedListener listener){
        Context app = context.getApplicationContext();
        ContentResolver cr = app.getContentResolver();
        return new AddNoteTask(cr,listener);
    }

    private AddNoteTask(ContentResolver cr,OnWorkStatusChangedListener listener) {
        super(cr);
        this.listener = listener;
    }


    public void addNote(int taskId,Note note){
        ContentValues values = new ContentValues();
        values.put(Contract.Note.TITLE,note.title);
        values.put(Contract.Note.CONTENT,note.content);
        values.put(Contract.Note.DATE,note.getDate());
        values.put(Contract.Note.FAVOURITE,note.favourite);
        startInsert(taskId, null, Contract.Note.CONTENT_URI,values);
        listener.onChangeState("Starting work");
    }

    @Override
    protected void onInsertComplete(int token, Object cookie, Uri uri) {
        super.onInsertComplete(token, cookie, uri);
        Log.d(TAG,"Inserted: "+uri);
        listener.onChangeState("Work done");
    }
}
