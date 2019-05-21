package com.toeic.activity.lesson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.toeic.R;

public class lesson_part1 extends AppCompatActivity {

    Button btnList, btnView, btnNext, btnPrev, btnExit;
    ImageView imgPicture;
    SeekBar sbAudio;
    RadioButton rbOptionA, rbOptionB, rbOptionC, rbOptionD;
    AlertDialog.Builder dialog;
    String[] question = {"question 1", "question 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_part1);
        init();
    }

    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnList = (Button) findViewById(R.id.btnList);
        btnView = (Button) findViewById(R.id.btnView);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnExit = (Button) findViewById(R.id.btnExit);

        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        sbAudio = (SeekBar) findViewById(R.id.seekBar);
        rbOptionA = (RadioButton) findViewById(R.id.optionA);
        rbOptionB = (RadioButton) findViewById(R.id.optionB);
        rbOptionC = (RadioButton) findViewById(R.id.optionC);
        rbOptionD = (RadioButton) findViewById(R.id.optionD);

    }

    public void showDialog() {
        

    }


    public void btnExit(View view) {

    }

    public void btnList(View view) {

        showDialog();
    }
}
