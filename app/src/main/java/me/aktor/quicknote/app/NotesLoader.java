package me.aktor.quicknote.app;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import me.aktor.quicknote.data.Contract;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
public abstract class NotesLoader  implements LoaderManager.LoaderCallbacks<Cursor>{

    private final Context mContext;

    NotesLoader(Context context){
        mContext = context;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        CursorLoader loader = new CursorLoader(mContext,
                Contract.Note.CONTENT_URI,null,null,null,
                Contract.Note.DATE+ " ASC");
        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        onNotesLoaded(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        onNotesLoaded(null);
    }

    public abstract void onNotesLoaded(Cursor cursor);
}
