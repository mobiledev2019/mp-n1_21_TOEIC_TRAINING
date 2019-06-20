package com.toeic.activity.lesson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.toeic.R;

public class getPoint extends AppCompatActivity {

    TextView txtTitle, txtAnswer, txtScope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_point);
        init();
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String answer  = intent.getStringExtra("answer");
        String scope = intent.getStringExtra("scope");

        txtTitle.setText(title);
        txtAnswer.setText(answer);
        txtScope.setText(scope);
    }

    private void init() {
        txtTitle = findViewById(R.id.title);
        txtAnswer = findViewById(R.id.answer);
        txtScope = findViewById(R.id.scope);
    }

}
