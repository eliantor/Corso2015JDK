package me.aktor.quicknote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Andrea Tortorella on 6/6/15.
 */
public class ResultShowActivity extends Activity
        implements View.OnClickListener {

    public static final String TEXT_PARAM = "text_param";
    public static final String TEXT_RESULT = "text_result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView view = (TextView)findViewById(R.id.tv_result);

        findViewById(R.id.btn_toupper).setOnClickListener(this);

        String param = getTextParam();

        view.setText(param);

    }

    private String getTextParam() {
        Intent intent = getIntent();
        return intent.getStringExtra(TEXT_PARAM);
    }

    @Override
    public void onClick(View v) {
        String text = getTextParam();
        String upper = text.toUpperCase();
        Intent response = new Intent();
        response.putExtra(TEXT_RESULT,upper);
        setResult(RESULT_OK,response);
        finish();
    }
}
