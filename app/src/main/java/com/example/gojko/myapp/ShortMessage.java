package com.example.gojko.myapp;


import com.google.gson.annotations.SerializedName;

public class ShortMessage {

    @SerializedName("created")
    private String created;

    @SerializedName("text")
    private String text;

    public ShortMessage(String created, String text) {
        this.created = created;
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public String getText() {
        return text;
    }
}
