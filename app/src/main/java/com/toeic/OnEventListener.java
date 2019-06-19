package com.toeic;

import org.json.JSONException;

public interface OnEventListener<JSONArray> {
    public void onSuccess(org.json.JSONArray jsonAray) throws JSONException;

    public void onFailure(Exception e);
}
