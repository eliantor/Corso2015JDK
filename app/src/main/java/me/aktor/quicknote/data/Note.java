package me.aktor.quicknote.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class Note implements Parcelable{

    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS");

    public final String title;
    public final String content;
    public final Date date;
    public final boolean favourite;

    public Note(String title, String content,boolean favourite) {
        this.title = title;
        this.content = content;
        this.date = new Date();
        this.favourite=favourite;
    }


    private Note(Parcel source) {
        title = source.readString();
        content = source.readString();
        Date d;
        try {
            d = FORMAT.parse(source.readString());
        } catch (ParseException e) {
            d = new Date();
        }
        date = d;
        favourite = source.readInt()==1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(FORMAT.format(date));
        dest.writeInt(favourite?1:0);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /*
     pincopallino_v_i(i)
     pincopallino(String[]) -> pincopallino_[Ljava.lang.String

     */


    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getDate() {
        return FORMAT.format(date);
    }

}
