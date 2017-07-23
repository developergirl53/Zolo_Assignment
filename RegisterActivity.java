package com.example.priyanka.zolo_assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ceswar on 23-07-2017.
 */

public class RegisterActivity extends LoginActivity {

    SharedPreferences pref;
    Editor editor;
    public TextView mLog_page = new TextView(this);
    public EditText mNumber, mEmail, mName, mPassword;
    public Integer number_text;
    public String email_text, name_text, pswd_text;
    Button mRegister;
    private String Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        final String regEX = "^(([w-]+.)+[w-]+|([a-zA-Z]{1}|[w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]).([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]).([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[w-]+.)+[a-zA-Z]{2,4})$";

        mNumber = (EditText) findViewById(R.id.number);
        mEmail = (EditText) findViewById(R.id.email);
        mName = (EditText) findViewById(R.id.name);
        mPassword = (EditText) findViewById(R.id.pswd);


        pref = getSharedPreferences(Register, 0);
        editor = pref.edit();

        mRegister = (Button) findViewById(R.id.register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number_text = Integer.valueOf(mNumber.getText().toString());
                email_text = mEmail.getText().toString();
                name_text = mName.getText().toString();
                pswd_text = mPassword.getText().toString();

                if ((mNumber.getText().length() <= 0) && (mNumber.getText().toString().equals(""))) {
                    Toast.makeText(RegisterActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                } else if ((mName.getText().length() <= 0) && (mName.getText().toString().equals(regEX))) {
                    Toast.makeText(RegisterActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (((!mEmail.getText().toString().equals(""))) && (mEmail.getText().toString().equals(regEX))) {
                    email_text = mEmail.getText().toString();
                    int at_the_rate = email_text.indexOf("@");
                    int dot_com = email_text.indexOf(".");

                    if (!(email_text.contains("@")) || (!(email_text.contains(".")))) {

                        Toast.makeText(getApplication(),
                                "Invalid Email", Toast.LENGTH_SHORT).show();

                    } else if ((at_the_rate <= 1) && (dot_com < 1)) {

                        Toast.makeText(getApplication(),
                                "Invalid Email", Toast.LENGTH_SHORT).show();


                    } else if ((dot_com <= 1) && (at_the_rate < 1)) {

                        Toast.makeText(getApplication(),
                                "Invalid Email", Toast.LENGTH_SHORT).show();


                    } else if ((email_text.length() - dot_com < 2)) {

                        Toast.makeText(getApplication(),
                                "Invalid Email", Toast.LENGTH_SHORT).show();

                    }

                } else if (mPassword.getText().length() <= 0 && (mPassword.getText().toString().equals(regEX))) {
                    Toast.makeText(RegisterActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    editor.putString("Phone Number", String.valueOf(number_text));
                    editor.putString("Name", name_text);
                    editor.putString("Email", email_text);
                    editor.putString("Password", pswd_text);
                    editor.commit();
                    mLog_page = new TextView(this);
                    mLog_page.setClickable(true);
                    mLog_page.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent re = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(re);
                        }
                    });
                }
            }
        });
    }
}


