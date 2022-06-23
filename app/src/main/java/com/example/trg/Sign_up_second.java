package com.example.trg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.androidgamesdk.gametextinput.Listener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Sign_up_second extends AppCompatActivity {

    private EditText username, password, passwordCheck;
    private Button round_next_signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page_2);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordCheck = findViewById(R.id.passwordCheck);

        round_next_signup_button = findViewById(R.id.round_next_signup_button);
        round_next_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String Password = password.getText().toString();
                String PasswordCheck = passwordCheck.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Toast.makeText(getApplicationContext(), "회원 등록 성공", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "회원 등록 실패", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(email, Password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Sign_up_second.this);
            }
        });
    }
}
