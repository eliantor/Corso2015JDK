package me.aktor.quicknote.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/27/15.
 */
public class WorkerFragment extends Fragment implements
        AddNoteTaskAsync.OnWorkStatusChangedListener {

    public static final String TAG = "WORKER";
    AddNoteTaskAsync mTask;

    LoginTask mLogin;

    private String mPendingState;

    private boolean mIsActivityPresent;

    AddNoteTaskAsync.OnWorkStatusChangedListener mListener;


    public static WorkerFragment create(){
        return new WorkerFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof AddNoteTask.OnWorkStatusChangedListener) {
            mListener = (AddNoteTaskAsync.OnWorkStatusChangedListener)
                    activity;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onResume() {
        super.onResume();
        mIsActivityPresent = true;
        if (mPendingState != null) {
            dispatch(mPendingState);
            mPendingState = null;
        }

    }

    @Override
    public void onPause() {
        mIsActivityPresent = false;
        super.onPause();
    }

    private void dispatch(String state){
        if (mListener != null && mIsActivityPresent){
            mListener.onChangeState(state);
        } else {
            mPendingState = state;
        }
    }

    public void startInsert(Note note){
        mTask = AddNoteTaskAsync.start(getActivity(),
                                        note,this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTask != null){
            mTask.cancel(false);
        }
        free(mPendingState);
        mPendingState = null;
    }

    private void  free(String state){

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onChangeState(String state) {
        dispatch(state);
        mTask = null;
    }

    public void login(String username, String password) {
        mLogin = new LoginTask(username,password);
        mLogin.execute();

    }
}
