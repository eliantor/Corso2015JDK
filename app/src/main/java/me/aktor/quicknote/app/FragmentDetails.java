package me.aktor.quicknote.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Andrea Tortorella on 6/27/15.
 */
public class FragmentDetails extends Fragment {

    public static final String NOTE_ID = "note_id";
    public static final String TAG = "FragmentDetails";

    public static FragmentDetails create(long noteId){
        FragmentDetails details = new FragmentDetails();
        Bundle args = new Bundle();
        args.putLong(NOTE_ID,noteId);
        return details;
    }

    private long mId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getLong(NOTE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
