package com.example.trg;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class LoginRequest extends StringRequest {

    final static private String URL = "https://a782-27-117-234-165.jp.ngrok.io/trg/doLogin";
    private Map<String, String> map;

    public LoginRequest(String user_id, String user_pw, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("user_pw", user_pw);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return map;
    }
}