package com.example.priyanka.zolo_assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText mNumber;
    private EditText mPassword;
    private Integer number_text;
    private String pswd_text;

    public Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        final String regEX = "^(([w-]+.)+[w-]+|([a-zA-Z]{1}|[w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]).([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]).([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[w-]+.)+[a-zA-Z]{2,4})$";

        String number = getMyPhoneNO();
        Toast.makeText(getApplicationContext(), "My Phone Number is: "
                + number, Toast.LENGTH_SHORT).show();

        mNumber = (EditText) findViewById(R.id.number);
        mPassword = (EditText) findViewById(R.id.pswd);
        Button mLogin = (Button) mPassword.findViewById(R.id.log_btn);
        mLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((!mNumber.getText().toString().equals("") && !mNumber.getText().toString().equals(regEX)) &&
                        (!mPassword.getText().toString().equals("") && !mPassword.getText().toString().equals(regEX))) {

                    if ((!mNumber.getText().toString().equals(""))) {
                        number_text = Integer.valueOf(mNumber.getText().toString());
                        pswd_text = mPassword.getText().toString();
                    } else if ((!mNumber.getText().toString().equals(""))) {
                        Toast.makeText(getApplication(),
                                "Password field is empty", Toast.LENGTH_SHORT).show();

                    } else if ((!mPassword.getText().toString().equals(""))) {
                        Toast.makeText(getApplication(),
                                "Phone Number field is empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplication(),
                                "Phone Number and Password field are empty", Toast.LENGTH_SHORT).show();
                    }

                } else {
                        try{
                            GetText();
                            hideKeyboard();
                        }
                        catch(Exception ex)
                        {
                            Toast.makeText(getApplicationContext(), " Error while Getting the value ", Toast.LENGTH_LONG ).show();
                        }
                }
            }
        });
    }

    public void GetText() {
        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
        number_text = Integer.valueOf(mNumber.getText().toString());
        pswd_text = mPassword.getText().toString();
        intent.putExtra("Phone Number", number_text);
        intent.putExtra("Password", pswd_text);
        startActivity(intent);
    }

    private void hideKeyboard() {
            InputMethodManager imm = (InputMethodManager)getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mNumber.getWindowToken(), 0);
            imm.hideSoftInputFromWindow(mPassword.getWindowToken(), 0);
    }

    public String getMyPhoneNO() {
        TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
        return mPhoneNumber;
    }
}

