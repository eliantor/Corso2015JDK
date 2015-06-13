package me.aktor.quicknote.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.aktor.quicknote.R;
import me.aktor.quicknote.data.FakeData;
import me.aktor.quicknote.data.Note;

/**
 * Created by Andrea Tortorella on 6/6/15.
 */
public class HelloWorldActivity extends Activity {

    private final static String CURRENT_TEXT = "CURRENT_TEXT";

    private static final int REQUEST_TO_UPPERCASE = 2;

    private TextView mTextView;
    private EditText mInput;

    private String mCurrentText;

    private final View.OnClickListener listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.btn_gen:
                    startResultActivity();
                    //updateText();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        View view = findViewById(R.id.tv_hello);
        mTextView = (TextView)view;
        mInput = (EditText)findViewById(R.id.in_text);

        findViewById(R.id.btn_gen).setOnClickListener(listener);

        if (savedInstanceState != null) {
            Log.d("TAG","Saved state");
            String text = savedInstanceState.getString(CURRENT_TEXT);
            updateText(text);
        }
    }


    private void startResultActivity(){
        Editable text =mInput.getText();
        Intent intent = new Intent(this,ResultShowActivity.class);
        intent.putExtra(ResultShowActivity.TEXT_PARAM,text.toString());
        //startActivity(intent);

        startActivityForResult(intent, REQUEST_TO_UPPERCASE);
    }

    private void updateText(){
        Editable text = mInput.getText();
        updateText(text.toString());
    }

    private void updateText(String text){
        mCurrentText = text;
        if (!TextUtils.isEmpty(mCurrentText)) {
            mTextView.setText(mCurrentText);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CURRENT_TEXT,mCurrentText);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_TO_UPPERCASE) {
            if (resultCode == RESULT_OK) {
                String response = data.getStringExtra(ResultShowActivity.TEXT_RESULT);
                Toast.makeText(this,"Maiuscolo: "+response,
                        Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}








