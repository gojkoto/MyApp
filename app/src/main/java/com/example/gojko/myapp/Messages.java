package com.example.gojko.myapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    @SerializedName("messages")
    public List<ShortMessage> messages = new ArrayList<>();

    public List<ShortMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ShortMessage> messages) {
        this.messages = messages;
    }

}
