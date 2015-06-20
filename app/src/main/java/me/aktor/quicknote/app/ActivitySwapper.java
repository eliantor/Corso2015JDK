package me.aktor.quicknote.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import me.aktor.quicknote.R;
import me.aktor.quicknote.app.list.FragmentList;

/**
 * Created by Andrea Tortorella on 6/13/15.
 */
public class ActivitySwapper extends FragmentActivity {

    private boolean mState;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swapper);
        findViewById(R.id.btn_swap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment f  = mState?new FragmentCreateNote():new FragmentList();
                mState = !mState;
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.host,f,"TAG")
                .commit();
            }
        });
    }
}
