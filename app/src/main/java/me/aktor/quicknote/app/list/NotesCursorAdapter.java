package me.aktor.quicknote.app.list;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.aktor.quicknote.R;
import me.aktor.quicknote.data.Contract;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/20/15.
 */
class NotesCursorAdapter extends CursorAdapter {

    private static class ViewHolder {
        TextView title;
        TextView date;

        public void bind(Cursor note) {
            int titleIdx = note.getColumnIndexOrThrow(Contract.Note.TITLE);
            int contentIdx =note.getColumnIndexOrThrow(Contract.Note.DATE);

            String titleString = note.getString(titleIdx);
            String dateString = note.getString(contentIdx);

            title.setText(titleString);
            date.setText(dateString);

        }
    }


    private final LayoutInflater mInflater;

    public NotesCursorAdapter(Context context) {
        super(context, /*Cursor*/null,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.item_note, parent, false);
        ViewHolder h = new ViewHolder();
        h.title =(TextView)view.findViewById(R.id.tv_note_title);
        h.date = (TextView)view.findViewById(R.id.tv_note_date);
        view.setTag(h);
        return view;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();
        holder.bind(cursor);
    }
}
