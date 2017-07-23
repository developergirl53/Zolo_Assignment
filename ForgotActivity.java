package com.example.priyanka.zolo_assignment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by ceswar on 23-07-2017.
 */

public class ForgotActivity extends LoginActivity {

    String email_text,;
    ImageView mImage_lock;
    public String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button reset_button;
    TextView mLog;
    EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            setContentView(R.layout.forgotpasswordxl);
        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            setContentView(R.layout.forgotpasswordl);
        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            setContentView(R.layout.forgotpasswordm);
        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            setContentView(R.layout.forgotpassword);
        } else {
            setContentView(R.layout.forgotpasswordm);
        }

        mImage_lock = (ImageView) findViewById(R.id.email_image);
        mEmail = (EditText) findViewById(R.id.email);
        mLog = (TextView) findViewById(R.id.log_in);
        mLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
            }
        });
        reset_button = (Button) findViewById(R.id.reset_btn);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_button.setEnabled(false);
                email_text = mEmail.getText().toString().trim();

                if (!email_text.matches(emailPattern)) {
                    reset_button.setEnabled(true);
                    mImage_lock.setVisibility(View.GONE);
                } else {
                    new Thread(verfication).start();
                }
            }
        });

        Intent i = new Intent(ForgotActivity.this,
                LoginActivity.class);
        Bundle extras = new Bundle();
        extras.putString("mEmail", email_text);
        i.putExtras(extras);
        startActivity(i);
        finish();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                reset_button.setEnabled(true);
                mImage_lock.setVisibility(View.GONE);
                return;
            }
        });
    }
}



