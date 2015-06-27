package me.aktor.quicknote.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.os.Build;
import android.os.Environment;

import java.io.File;

import me.aktor.quicknote.BuildConfig;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
class NoteDbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME =
//            BuildConfig.DEBUG
//                    ? new File(Environment.getExternalStorageDirectory(),"db_file.db").getAbsolutePath()
//                    :
            "quick_note.db";

    private static final int DB_VERSION = 3;


    public static final SQLiteDatabase.CursorFactory FACTORY =
            new SQLiteDatabase.CursorFactory() {
                @Override
                public Cursor newCursor(SQLiteDatabase db,
                                        SQLiteCursorDriver masterQuery,
                                        String editTable,
                                        SQLiteQuery query) {
                    return null;
                }
            };



    public NoteDbOpenHelper(Context context) {
        super(context,
                DB_NAME,
        /*SQLiteDatabase.CursorFactory*/null,
                DB_VERSION);
    }

    private static final String CREATE_NOTE_TABLE =
            "CREATE TABLE IF NOT EXISTS "+Contract.Note.PATH+" ("
             +Contract.Note._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
             +Contract.Note.TITLE + " TEXT NOT NULL,"
             +Contract.Note.CONTENT+ " TEXT NOT NULL, "
             +Contract.Note.FAVOURITE+ " INTEGER DEFAULT 0,"
             +Contract.Note.DATE+ " TEXT NOT NULL"
             +")";

    private static final String DROP_NOTE_TABLE =
            "DROP TABLE "+Contract.Note.PATH;



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL(DROP_NOTE_TABLE);
            onCreate(db);
        }
    }
}
