package me.aktor.quicknote.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
public final class Contract {
    ///potenzialmente publlica visibile anche fuori dalla nostra app

    public static final String AUTHORITY ="me.aktor.quicknote";

    public static final Uri CONTENT_URI =Uri.parse("content://"+AUTHORITY);


    private Contract(){}

    public static final class Note {
        private Note(){}

        static final String PATH = "note"; // tabella

        public static final Uri CONTENT_URI = Contract.CONTENT_URI.buildUpon()
                .appendPath(PATH).build();
        // content://me.aktor.quicknote/note

        public static Uri getNote(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final String _ID = BaseColumns._ID; //"_id"
        public static final String TITLE = "title";
        public static final String CONTENT = "content";
        public static final String FAVOURITE = "favourite";
        public static final String DATE = "date";

    }
}
