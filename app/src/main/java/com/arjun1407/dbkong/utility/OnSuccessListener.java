package com.arjun1407.dbkong.utility;

import org.json.JSONObject;

public interface OnSuccessListener {
    void onSuccess(JSONObject response);
    void onFailure(Error error);
}


