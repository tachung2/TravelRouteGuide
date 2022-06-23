package com.example.trg;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.androidgamesdk.gametextinput.Listener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Sign_up_second extends AppCompatActivity {

    private EditText username, password, passwordCheck;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page_2);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordCheck = findViewById(R.id.passwordCheck);
        ImageButton button = findViewById(R.id.backButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton round_next_signup_button = findViewById(R.id.round_next_signup_button);
        round_next_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String PW = password.getText().toString();
                String Check = passwordCheck.getText().toString();

                if (email.equals("") || PW.equals("") || Check.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Sign_up_second.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                } if (!PW.equals(Check)) {
                    Toast.makeText(Sign_up_second.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    password.setText("");
                    passwordCheck.setText("");
                    password.requestFocus();
                    return;
                } else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("response_type");
                                if (success.equals("100")) {
                                    Toast.makeText(Sign_up_second.this, "회원 등록 성공", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Sign_up_second.this, Sign_up_first.class);
                                    intent.putExtra("user_id", email);
                                    startActivity(intent);
                                }
                                if (success.equals("101")) {
                                    Toast.makeText(Sign_up_second.this, "이미 가입한 아이디가 있습니다.", Toast.LENGTH_LONG).show();
                                    return;
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    RegisterRequest registerRequest = new RegisterRequest(email, PW, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(Sign_up_second.this);
                    queue.add(registerRequest);
                }
            }
        });
    }
}
