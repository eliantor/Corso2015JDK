package me.aktor.quicknote.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
class Dao {


    public static Cursor getAllNotes(NoteDbOpenHelper helper,
                                     String select,String order){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(Contract.Note.PATH, null, select,
                null, null, null, order);

        return cursor;
    }

    public static Cursor getNote(NoteDbOpenHelper helper,long id){
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor =db.query(Contract.Note.PATH, null,
                Contract.Note._ID + "  =  " + id,
                null,
                null, null, null
        );
        return cursor;

    }

    public static long insertNote(NoteDbOpenHelper helper,
                                  ContentValues values){
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(Contract.Note.PATH, null, values);
            // se fallisce l'id è -1
        return id;
    }
}
