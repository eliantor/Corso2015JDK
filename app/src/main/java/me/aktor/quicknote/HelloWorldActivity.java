package me.aktor.quicknote;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Andrea Tortorella on 6/6/15.
 */
public class HelloWorldActivity extends Activity {

    private final static String CURRENT_TEXT = "CURRENT_TEXT";

    private TextView mTextView;
    private EditText mInput;

    private String mCurrentText;

    private final View.OnClickListener listener =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.btn_gen:
                    updateText();
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
            String text = savedInstanceState.getString(CURRENT_TEXT);

            updateText(text);
        }
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
}
