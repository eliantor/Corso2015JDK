package me.aktor.quicknote.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import me.aktor.quicknote.R;

/**
 * Created by Andrea Tortorella on 6/27/15.
 */
public class ActivityDetails extends FragmentActivity {

    public static void start(Context context,long id){
        Intent intent = new Intent(context,ActivityDetails.class);
        intent.putExtra(FragmentDetails.NOTE_ID,id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        if (savedInstanceState == null){
            long id = getIntent().getLongExtra(FragmentDetails.NOTE_ID,-1);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.host,
                            FragmentDetails.create(id),
                            FragmentDetails.TAG)
                    .commit();
        }
    }
}
