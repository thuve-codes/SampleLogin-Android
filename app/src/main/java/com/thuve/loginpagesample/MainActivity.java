package com.thuve.loginpagesample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnlogin, mainenbtn, regbtn, createbtn;
    EditText un, epass;
    TextView errortxt;

    RequestQueue requestQueue; // Class-level variable for RequestQueue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainen);

        // Initialize UI elements
        mainenbtn = findViewById(R.id.button);
        requestQueue = Volley.newRequestQueue(this); // Initialize RequestQueue

        // Set up main button click
        mainenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupLoginView();
            }
        });
    }

    private void setupLoginView() {
        setContentView(R.layout.activity_main);

        // Initialize login UI elements
        btnlogin = findViewById(R.id.loginbtn);
        un = findViewById(R.id.enterun);
        epass = findViewById(R.id.enterpassword);
        errortxt = findViewById(R.id.error);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAPIRequest();
            }
        });

        // Set up registration button
        regbtn = findViewById(R.id.regbtn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupSignupView();
            }
        });
    }

    private void setupSignupView() {
        setContentView(R.layout.signuppage);

        // Initialize signup UI elements
        createbtn = findViewById(R.id.createid);
        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Registration Successful, Login with Credentials", Toast.LENGTH_LONG).show();
                setupLoginView();
            }
        });
    }

    private void setAPIRequest() {
        String jsonUrl = "https://api.jsonserve.com/pnsoEE";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, jsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("userdet");
                            boolean userFound = false;

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject user = jsonArray.getJSONObject(i);
                                String name = user.getString("username");
                                String password = user.getString("password");

                                if (name.equals(un.getText().toString())) {
                                    userFound = true;
                                    if (password.equals(epass.getText().toString())) {
                                        setContentView(R.layout.mainpage);
                                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                        return;
                                    } else {
                                        errortxt.setText("Incorrect password!");
                                        return;
                                    }
                                }
                            }

                            if (!userFound) {
                                errortxt.setText("Username not found!");
                            }
                        } catch (JSONException e) {
                            errortxt.setText("Error parsing response!");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errortxt.setText("Error connecting to server!");
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
