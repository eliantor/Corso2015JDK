package me.aktor.quicknote.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import me.aktor.quicknote.R;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class MainFragmentActivity extends FragmentActivity implements FragmentCreateNote.OnSaveNoteListener {

    public static final int TASK_ADD = 1;
    //private FragmentCreateNote mCreate;
    //private FragmentList mList;
    private AddNoteTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);

        mTask = AddNoteTask.newInstance(this);
        //FragmentManager fm = getSupportFragmentManager();
        //mCreate =(FragmentCreateNote) fm.findFragmentById(R.id.FragmentInsert);
        //mList = (FragmentList)fm.findFragmentById(R.id.FragmentList);

    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof FragmentCreateNote) {
            ((FragmentCreateNote) fragment).setOnSaveNoteListener(this);
        }
    }

    public void addNote(Note note){
        //mList.addNote(note);
        // todo add to database
        mTask.addNote(TASK_ADD,note);
    }

    @Override
    public void onSaveNote(Note note) {
        addNote(note);
    }
}
