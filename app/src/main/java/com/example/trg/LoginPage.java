package com.example.trg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    private ImageButton backBtn;
    private TextView username, password;
    private android.widget.Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        loginBtn = (android.widget.Button) findViewById(R.id.login);
        backBtn = (ImageButton) findViewById(R.id.backButton);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = username.getText().toString();
                String user_pw = password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (!(username.length() == 0 || password.length() == 0)){
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                String success = jsonResponse.getString("response_type");
                                if (success.equals("120")) {
                                    Toast.makeText(getApplicationContext(), "로그인에 성공했습니다.", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginPage.this, Route_Guide.class);
                                    intent.putExtra("user_id", user_id);
                                    startActivity(intent);
                                    finish();
                                } if (success.equals("121")) {
                                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                                } if (success.equals("122")) {
                                    Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(user_id, user_pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginPage.this);
                queue.add(loginRequest);

            }
        });
    }
}
