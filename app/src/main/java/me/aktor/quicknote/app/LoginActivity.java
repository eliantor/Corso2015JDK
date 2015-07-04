package me.aktor.quicknote.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.aktor.quicknote.R;

/**
 * Created by Andrea Tortorella on 7/4/15.
 */
public class LoginActivity extends FragmentActivity implements View.OnClickListener {

    private EditText mUsername;
    private EditText mPassword;

    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername = (EditText)findViewById(R.id.in_username);
        mPassword = (EditText)findViewById(R.id.in_password);
        findViewById(R.id.btn_login).setOnClickListener(this);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(WorkerFragment.create(),WorkerFragment.TAG)
                    .commit();
        }

    }

    @Override
    public void onClick(View v) {
        WorkerFragment f =(WorkerFragment) getSupportFragmentManager()
                .findFragmentByTag(WorkerFragment.TAG);
        f.login(mUsername.getText().toString(),
                mPassword.getText().toString());

    }
}
