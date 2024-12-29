package com.thuve.loginpagesample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnlogin,mainenbtn,regbtn,createbtn;
    EditText un,epass;
    TextView errortxt;
    String jsonurl="https://raw.githubusercontent.com/thuve-codes/logindet-json/refs/heads/main/db.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainen);
        mainenbtn=findViewById(R.id.button);

        mainenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                login();


                regbtn = findViewById(R.id.regbtn);
                regbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.signuppage);

                        createbtn = findViewById(R.id.createid);
                        createbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Reg Sucess, Login with Credentials", Toast.LENGTH_LONG).show();
                                login();
                            }
                        });
                    }
                });

            }




            //--------------------------------
            void login() {
                 setContentView(R.layout.activity_main);
                 btnlogin = findViewById(R.id.loginbtn);
                 un = findViewById(R.id.enterun);
                 epass = findViewById(R.id.enterpassword);
                 errortxt = findViewById(R.id.error);
                btnlogin.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        if (vailduser(un.getText().toString(), epass.getText().toString())) {
                            // errortxt.setText("");
                            setContentView(R.layout.mainpage);

                            Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_LONG).show();

                        } else {
                            errortxt.setVisibility(v.VISIBLE);
                        }
                    };

                });
            }


            //----------------------
            //Json Handling


            //----------------------

            private boolean vailduser(String un,String pwd) {
                if (un.length() < 4) {
                    errortxt.setText("Username must be greater than 4 characters");
                    return false;
                };

                //TODO: check user name is in json - for loop
                if (un.equals("admin")) {
                    //TODO: if found check given password is match with json
                    // errortxt.setText("Correct Credentials");
                    return true;
                }
                return false;
            };

        });
    };

};