package me.aktor.quicknote.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import me.aktor.quicknote.R;
import me.aktor.quicknote.data.FakeData;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class FragmentList extends Fragment {

    private NotesAdapter mAdapter;
    private ListView mList;
    private List<Note> mNotes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list,container,false);
        mNotes = new ArrayList<>();
        List<Note> notes = FakeData.generateMany(50);
        mNotes.addAll(notes);
        mList = (ListView)v.findViewById(R.id.list);
        mAdapter = new NotesAdapter(getActivity(),mNotes);
        mList.setOnItemClickListener(mAdapter);
        mList.setAdapter(mAdapter);
        return v;
    }

    public void addNote(Note note) {
        mNotes.add(0,note);
        mAdapter.notifyDataSetChanged();
    }


    private static class NotesAdapter extends BaseAdapter
            implements AdapterView.OnItemClickListener {

        private final List<Note> mAdapterNotes;
        private Context mContext;
        private final LayoutInflater mInflater;
        java.text.DateFormat sdf;

        private NotesAdapter(Context context,List<Note> notes){
            mContext = context;
            mAdapterNotes = notes;
            mInflater =  LayoutInflater.from(context);
            sdf = SimpleDateFormat.getDateInstance();
        }

        @Override
        public int getCount() {
            return mAdapterNotes.size();
        }

        @Override
        public Note getItem(int position) {
            return mAdapterNotes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Note note = getItem(position);
            Toast.makeText(mContext, note.content, Toast.LENGTH_LONG).show();
        }

        private static class ViewHolder {
            TextView title;
            TextView date;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder h;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_note, parent, false);
                h = new ViewHolder();
                h.title =(TextView)convertView.findViewById(R.id.tv_note_title);
                h.date = (TextView)convertView.findViewById(R.id.tv_note_date);
                convertView.setTag(h);
            } else {
                h = (ViewHolder)convertView.getTag();
            }


            Note note = getItem(position);
            h.date.setText(sdf.format(note.date));
            h.title.setText(note.title);
            return convertView;
        }
    }
}
