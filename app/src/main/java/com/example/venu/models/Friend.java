package com.example.venu.models;

import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Friend {
    String name;
    String friendPhoto;

    public Friend(ParseObject friend) {
        name = friend.get("username").toString();
        friendPhoto = friend.getParseFile("profilepicture").getUrl();
    }

    public String getName() {
        return name;
    }

    public String getfriendPhotoUrl() {
        return friendPhoto;
    }

}