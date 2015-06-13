package me.aktor.quicknote.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class Note implements Serializable{

    public final String title;
    public final String content;
    public final Date date;

    public Note(String title, String content, Date date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }


}
