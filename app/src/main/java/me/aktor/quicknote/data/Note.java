package me.aktor.quicknote.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class Note implements Parcelable{

    public final String title;
    public final String content;
    public final Date date;

    public Note(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    private Note(Parcel source) {
        title = source.readString();
        content = source.readString();
        date = null;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
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

}
