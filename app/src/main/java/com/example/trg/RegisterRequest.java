package com.example.trg;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {


    // 서버 URL 설정
    final static private String URL = "https://a782-27-117-234-165.jp.ngrok.io//trg/doJoin";
    private Map<String, String> map;
    public RegisterRequest(String user_id, String user_pw, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_pw", user_pw);
        map.put("user_id", user_id);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
