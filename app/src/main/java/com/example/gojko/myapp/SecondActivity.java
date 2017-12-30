package com.example.gojko.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {

    private static final String TEXT_VIEW_KEY = "txVK";
    private static final String MESSAGE_STATE_KEY = "text";

    private String outMessage;
    private TextView textView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView1 = findViewById(R.id.textView1);

        Intent intent = getIntent();
        String consumerKey = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // recovering the instance state
        if (savedInstanceState != null) {
            outMessage = savedInstanceState.getString(MESSAGE_STATE_KEY);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiShortMessage.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiShortMessage apiShortMessage = retrofit.create(ApiShortMessage.class);

        Call<Messages> call = apiShortMessage.getShortMessage(consumerKey);


        call.enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                call=null;

                if(response.isSuccessful()) {

                    List<ShortMessage> messageList = response.body().getMessages();
                    if(messageList.size() >0) {
                        String[] Message = new String[messageList.size()];
                        for (int i = 0; i < messageList.size(); i++) {
                            Message[i] = messageList.get(i).getText();
                        }
                        textView1.setText(Message[0]);
                    }else {
                        Toast.makeText(getApplicationContext(),
                                "No new messages or please check the Key",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            response.errorBody().toString(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Nothing",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        textView1.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(MESSAGE_STATE_KEY, outMessage);
        outState.putString(TEXT_VIEW_KEY, (String) textView1.getText());

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

}
