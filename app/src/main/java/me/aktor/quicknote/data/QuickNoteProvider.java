package me.aktor.quicknote.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
/*public perchè deve essere visibile al sistema
* ma nessuno la usa*/
public class QuickNoteProvider extends ContentProvider {

    private static final int ALL_NOTES = 1;

    private static final int ONE_NOTE = 2;

    private static final int NOTE_BY_TITLE = 3;
    private static final String TAG = "QuickNoteProvider";


    private NoteDbOpenHelper mHelper;
    private static final UriMatcher MATCHER = createMatcher();


    private static UriMatcher createMatcher(){
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(Contract.AUTHORITY,Contract.Note.PATH,ALL_NOTES);
        matcher.addURI(Contract.AUTHORITY,Contract.Note.PATH+"/#",ONE_NOTE);
        //matcher.addURI(Contract.AUTHORITY,Contract.Note.PATH+"/*",NOTE_BY_TITLE);


        return matcher;
    }

    @Override
    public boolean onCreate() {
        mHelper = new NoteDbOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        int match = MATCHER.match(uri);
        Cursor cursor;
        switch (match){
            case ALL_NOTES:
                cursor = Dao.getAllNotes(mHelper,selection,sortOrder);
                break;
            case ONE_NOTE:
                long id = ContentUris.parseId(uri);
                cursor = Dao.getNote(mHelper,id);
                break;
            default:
                cursor = null;
        }

        if (cursor  != null){

            cursor.setNotificationUri(
                    getContext().getContentResolver(),
                    uri);
        }
        // todo special powers

        return cursor;
    }

    @Override
    public String getType(Uri uri) {

        int match  = MATCHER.match(uri);
        switch (match){
            case ALL_NOTES:
                return ContentResolver.CURSOR_DIR_BASE_TYPE+"/vnd.com.aktor.quicknote";
            case ONE_NOTE:
                return ContentResolver.CURSOR_ITEM_BASE_TYPE+"/vnd.com.aktor.quicknote";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = MATCHER.match(uri);
        if (match == ALL_NOTES){
            if (values.containsKey(Contract.Note._ID)){
                values.remove(Contract.Note._ID);
                //return null;
            }
            long id = Dao.insertNote(mHelper, values);
            if (id == -1){
                return null;
            }
            Log.d(TAG,"New note added: "+id);

            Uri ret = ContentUris.withAppendedId(Contract.Note.CONTENT_URI,id);
            //todo special powers
            if (ret != null){
                getContext().getContentResolver()
                        .notifyChange(Contract.Note.CONTENT_URI,null);
            }

            return ret;

        } else {
            throw new UnsupportedOperationException("non puoi inserire a questo path");
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
