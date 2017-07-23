package com.example.priyanka.zolo_assignment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by ceswar on 23-07-2017.
 */

public class ProfileActivity extends RegisterActivity {

    public static android.content.SharedPreferences SharedPreferences = null;

    private static final String PREFER_NAME = null;

    public EditText mNumber, mEmail, mName;
    public Integer number_text;
    public String email_text, name_text;
    Button mUpdate;
    android.content.SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        mNumber = (EditText) findViewById(R.id.number);
        mEmail = (EditText) findViewById(R.id.email);
        mName = (EditText) findViewById(R.id.name);

        mUpdate = (Button)findViewById(R.id.button_update);
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadPreferences();
                mUpdate.setFocusable(true);
                mUpdate.setClickable(true);
                editor = pref.edit();
                pref  = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                editor.putString("Phone Number", String.valueOf(number_text));
                editor.putString("Name", name_text);
                editor.putString("Email", email_text);
                editor.commit();

            }

        });
    }

    public void LoadPreferences() {
        android.content.SharedPreferences sharedpreferences = getSharedPreferences(String.valueOf(pref),
                Context.MODE_PRIVATE);
        mName.setText(sharedpreferences.getString(name_text, ""));
        mEmail.setText(sharedpreferences.getString(email_text, ""));
        mNumber.setText(sharedpreferences.getString(String.valueOf(number_text), ""));
    }


}
