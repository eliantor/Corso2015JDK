package me.aktor.quicknote.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.TextView;

import me.aktor.quicknote.R;
import me.aktor.quicknote.app.list.FragmentList;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class MainFragmentActivity extends FragmentActivity
        implements FragmentCreateNote.OnSaveNoteListener,
        AddNoteTaskAsync.OnWorkStatusChangedListener,FragmentList.OnShowNoteDetailsListener{

    public static final String TAG = "ACTIVITY";

    public static final int TASK_ADD = 1;
    //private FragmentCreateNote mCreate;
    //private FragmentList mList;
    //private AddNoteTaskAsync mTask;

    private TextView mStatusText;

    private WorkerFragment mWorker;
    private boolean mHasInlineDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragments);
        mHasInlineDetails =findViewById(R.id.host) != null;
        Log.w(TAG,"Activity: "+this.toString());

        mStatusText = (TextView)findViewById(R.id.status);
        FragmentManager m = getSupportFragmentManager();

        if (savedInstanceState  == null){
            mWorker = WorkerFragment.create();
            m.beginTransaction()
             .add(mWorker,WorkerFragment.TAG)
             .commit();
        } else {
            mWorker = (WorkerFragment)
                    m.findFragmentByTag(WorkerFragment.TAG);
        }



        //FragmentManager fm = getSupportFragmentManager();
        //mCreate =(FragmentCreateNote) fm.findFragmentById(R.id.FragmentInsert);
        //mList = (FragmentList)fm.findFragmentById(R.id.FragmentList);

    }


    @Override
    public void onChangeState(String state) {
        mStatusText.setText(state);
        Log.w(TAG,"Activity: "+this.toString());
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof FragmentCreateNote) {
            ((FragmentCreateNote) fragment).setOnSaveNoteListener(this);
        }
    }

    public void addNote(Note note){
        mWorker.startInsert(note);
    }


    @Override
    public void onSaveNote(Note note) {
        addNote(note);
    }

    @Override
    public void onShowDetails(long id) {
        if (mHasInlineDetails) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.host,
                            FragmentDetails.create(id),
                            FragmentDetails.TAG)
                    .commit();
        } else {
            ActivityDetails.start(this,id);
        }
    }
}
