package com.example.gojko.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static String EXTRA_MESSAGE = "MSG";
    Button getMsgButton;
    EditText editConsumerKey;
    private String consumerKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMsgButton = findViewById(R.id.button);
        editConsumerKey = findViewById(R.id.txtConsumerKey);
        getMsgButton.setEnabled(false);

        editConsumerKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getMsgButton.setEnabled(true);
                consumerKey = editConsumerKey.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void getMessage(View view){
        //Log.d("KEY ", consumerKey);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, consumerKey);
        startActivity(intent);
    }



}
