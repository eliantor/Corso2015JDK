package me.aktor.quicknote.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.aktor.quicknote.R;
import me.aktor.quicknote.data.FakeData;
import me.aktor.quicknote.data.Note;

public class MainActivity extends AppCompatActivity {

    private ListView mList;
    private ArrayList<Note> mNotes;
    private NotesAdapter mAdapter;

    private EditText mIntitle;
    private EditText mIncontent;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addNote();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNotes = new ArrayList<>();
        List<Note> notes = FakeData.generateMany(50);
        mNotes.addAll(notes);

        setContentView(R.layout.activity_main);

        mAdapter = new NotesAdapter(this,mNotes);

        mList = (ListView)findViewById(R.id.list);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(mAdapter);

        mIntitle = (EditText)findViewById(R.id.in_note_title);
        mIncontent = (EditText)findViewById(R.id.in_note_content);
        findViewById(R.id.btn_add_note).setOnClickListener(listener);

        QuickNoteApp.get().setVar("ciao");

      //  setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
    }

    private void addNote(){
        String title = mIntitle.getText().toString();
        String testo = mIncontent.getText().toString();
        Note note  = new Note(title,testo,false);
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
            Toast.makeText(mContext,note.content,Toast.LENGTH_LONG).show();
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

    private void startSomething(Note note){
        Intent i = new Intent();
        i.putExtra("PARAM",note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
