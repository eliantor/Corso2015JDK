package me.aktor.quicknote.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Date;

import me.aktor.quicknote.R;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class FragmentCreateNote extends Fragment{

    EditText mIntitle;
    EditText mIncontent;

    OnSaveNoteListener mSaveListener;

    public interface OnSaveNoteListener {
        public void onSaveNote(Note note);
    }

    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mSaveListener != null) {
                Note note = createNote();
                mSaveListener.onSaveNote(note);
            }

//            FragmentActivity activity = getActivity();
//            MainFragmentActivity f  = (MainFragmentActivity)activity;
//            f.addNote(note);
        }
    };


    public void setOnSaveNoteListener(OnSaveNoteListener listener){
        this.mSaveListener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert,container,false);

        mIntitle = (EditText)view.findViewById(R.id.in_note_title);
        mIncontent = (EditText)view.findViewById(R.id.in_note_content);
        view.findViewById(R.id.btn_add_note).setOnClickListener(listener);
        return view;
    }

    private Note createNote(){
        String title=mIntitle.getText().toString();
        String content=mIncontent.getText().toString();
        Date date = new Date();
        Note note = new Note(title,content,date);
        return  note;
    }
}
