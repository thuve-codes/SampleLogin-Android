package com.thuve.loginpagesample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnlogin,mainenbtn,regbtn,createbtn;
    EditText un,epass;
    TextView errortxt;

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

            private boolean vaildun(String un) {
                if (un.length() < 4) {
                    errortxt.setText("Username must be greater than 4 characters");
                    return false;
                };

                if (un.equals("admin")) {
                    // errortxt.setText("Correct Credentials");
                    return true;
                }
                return false;
            };


             void login() {
                 setContentView(R.layout.activity_main);
                 btnlogin = findViewById(R.id.loginbtn);
                 un = findViewById(R.id.enterun);
                 epass = findViewById(R.id.enterpassword);
                 errortxt = findViewById(R.id.error);
                btnlogin.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        if (vaildun(un.getText().toString()) && epass.getText().toString().equals("admin")) {
                            // errortxt.setText("");
                            setContentView(R.layout.mainpage);

                            Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_LONG).show();

                        } else {
                            errortxt.setVisibility(v.VISIBLE);
                        }
                    };

                });
            }

        });
    };

};