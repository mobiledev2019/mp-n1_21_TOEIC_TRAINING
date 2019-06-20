package com.toeic.activity.lesson;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.toeic.R;
import com.toeic.model.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class practice_part1 extends AppCompatActivity {

    private Button start, pause, next;
    private Button btn1, btn2, btn3, btn4, btn5;
    private Button btnNext , btnPrevious;
    private ImageView img_part1;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private Handler threadHandler = new Handler();
    private TextView textMaxTime;
    private TextView textCurrentPosition;
    private List<part1> listPart1;
    private List answer;
    static  int index=0;
    static int qu1=1, qu2=2, qu3=3, qu4=4, qu5=5;
    private RadioGroup radioGroup;
    private RadioButton radioOptionA, radioOptionB, radioOptionC, radioOptionD;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Dialog dialog;
    private Button btnLogout;

    private ArrayList listAnwer;
    private int totalAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_part1);

        initPart1();
        img_part1 = (ImageView) findViewById(R.id.img_part1);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnPrevious.setEnabled(false);

        radioGroup = findViewById(R.id.radioGroup);
        radioOptionA = findViewById(R.id.radioOptionA);
        radioOptionB = findViewById(R.id.radioOptionB);
        radioOptionC = findViewById(R.id.radioOptionC);
        radioOptionD = findViewById(R.id.radioOptionD);

        this.textCurrentPosition = (TextView)this.findViewById(R.id.textView_currentPosion);
        this.textMaxTime=(TextView) this.findViewById(R.id.textView_maxTime);
        start = (Button) findViewById(R.id.button1);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.seekBar.setClickable(false);
        //mp = MediaPlayer.create(this, R.raw.test);
        sharedPreferences
                = getApplicationContext().getSharedPreferences("answer_part1", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();

        playMediaPlayer(listPart1.get(0).getAudio());
//        this.mediaPlayer.start();
    }

    private void initPart1() {
        listPart1 = new ArrayList();
        listPart1.add(new part1("1", R.drawable.img_part12, R.raw.part1,"A"));
        listPart1.add(new part1("3", R.drawable.img_part1, R.raw.part1_2,"B"));
        listPart1.add(new part1("1", R.drawable.img_part12, R.raw.part1,"C"));
        listPart1.add(new part1("3", R.drawable.img_part1, R.raw.part1_2,"D"));
        listPart1.add(new part1("3", R.drawable.img_part12, R.raw.part1,"A"));
        listPart1.add(new part1("1", R.drawable.img_part12, R.raw.part1,"C"));
        listPart1.add(new part1("3", R.drawable.img_part1, R.raw.part1_2,"C"));
        listPart1.add(new part1("1", R.drawable.img_part12, R.raw.part1,"D"));
        listPart1.add(new part1("3", R.drawable.img_part1, R.raw.part1_2,"A"));
        listPart1.add(new part1("3", R.drawable.img_part12, R.raw.part1,"B"));
    }


    // Tìm ID của một file nguồn trong thư mục 'raw' theo tên.
    public int getRawResIdByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        // Trả về 0 nếu không tìm thấy.
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }


    // Chuyển số lượng milli giây thành một String có ý nghĩa.
    private String millisecondsToString(int milliseconds)  {
        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) milliseconds);
        long seconds =  TimeUnit.MILLISECONDS.toSeconds((long) milliseconds) ;
        return minutes+":"+ seconds;
    }

    // Khi người dùng click vào Button "Start".
    public void doStart(View view)  {

        // Khoảng thời gian của bài hát (Tính theo mili giây).
        int duration = this.mediaPlayer.getDuration();

        int currentPosition = this.mediaPlayer.getCurrentPosition();
        String maxTimeString = this.millisecondsToString(duration);
        if(currentPosition== 0)  {
            this.seekBar.setMax(duration);

            this.textMaxTime.setText(maxTimeString);
        } else if(currentPosition== duration)  {

            // Trở lại trạng thái ban đầu trước khi chơi.
            this.mediaPlayer.reset();
        }

        this.mediaPlayer.start();

        // Tạo một thread để update trạng thái của SeekBar.
        UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread,50);

        this.start.setEnabled(false);

    }

    // play video
    private void playMediaPlayer(int uri) {
        //creating media player

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer = MediaPlayer.create(this, uri);
//            mp.prepare();
//        mediaPlayer.start();
    }

    private void setSeekBar() {
        this.seekBar.setProgress(0);
        int duration = this.mediaPlayer.getDuration();

        int currentPosition = this.mediaPlayer.getCurrentPosition();
        String maxTimeString = this.millisecondsToString(duration);
        if(currentPosition== 0)  {
            this.seekBar.setMax(duration);

//            this.textMaxTime.setText(maxTimeString);
        } else if(currentPosition == duration)  {

            // Trở lại trạng thái ban đầu trước khi chơi.
            this.mediaPlayer.reset();
        }

        this.mediaPlayer.start();

        // Tạo một thread để update trạng thái của SeekBar.
        UpdateSeekBarThread updateSeekBarThread= new UpdateSeekBarThread();
        threadHandler.postDelayed(updateSeekBarThread,50);

        this.start.setEnabled(false);
    }

    public void previous(View view) {
        if(index > 1) {
            btnNext.setEnabled(true);
            index--;
            qu1--;
            qu2--;
            qu3--;
            qu4--;
            qu5--;
            setButton();

        } else {
            btnPrevious.setEnabled(false);
        }

    }

    private void setButton()
    {

        btn1.setText(String.valueOf(qu1));
        btn2.setText(String.valueOf(qu2));
        btn3.setText(String.valueOf(qu3));
        btn4.setText(String.valueOf(qu4));
        btn5.setText(String.valueOf(qu5));
    }

    public void next(View view) {
        if(index < 9) {
            btnPrevious.setEnabled(true);
            index++;
            qu1++;
            qu2++;
            qu3++;
            qu4++;
            qu5++;
            setButton();

        } else {
            btnNext.setEnabled(false);
        }
    }

    public void btn1(View view) {

        img_part1.setImageResource(listPart1.get(qu1).getImages());
        Toast.makeText(this, listPart1.get(qu1).getId(), Toast.LENGTH_SHORT).show();
//        playMediaPlayer(listPart1.get(qu1).getAudio());
//        setSeekBar();
    }

    public void btn2(View view) {
        mediaPlayer.stop();
        img_part1.setImageResource(listPart1.get(qu2).getImages());
        playMediaPlayer(listPart1.get(qu2).getAudio());
        setSeekBar();
    }

    public void btn3(View view) {

        mediaPlayer.stop();
        img_part1.setImageResource(listPart1.get(qu3).getImages());
        playMediaPlayer(listPart1.get(qu3).getAudio());
        setSeekBar();
    }

    public void btn4(View view) {
        mediaPlayer.stop();
        img_part1.setImageResource(listPart1.get(qu4).getImages());
        playMediaPlayer(listPart1.get(qu4).getAudio());
        setSeekBar();
    }

    public void btn5(View view) {
        mediaPlayer.stop();
        img_part1.setImageResource(listPart1.get(qu5).getImages());
        playMediaPlayer(listPart1.get(qu5).getAudio());
        setSeekBar();
    }

    public void submit(View view) {
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        scope();

//        this.seekBar.setProgress(0);
    }

    public int getPoint() {
        int scope = 0;
        for (int i = 0; i < listPart1.size(); i++) {
            if (sharedPreferences.contains(String.valueOf(i))) {
                if (listPart1.get(i).getAnswer().equals(sharedPreferences.getString(String.valueOf(i), ""))) {
                    scope++;
                }
            }
        }

        Log.d("scope", "" + scope);
        return scope;
    }
    public void scope() {
        Intent intent = new Intent(getApplicationContext(), getPoint.class);

        intent.putExtra("title", "Test 1");
        intent.putExtra("scope", String.valueOf(getPoint()));
        intent.putExtra("anwser", String.valueOf(totalAnswer));
        this.startActivity(intent);
    }

    public void showDialog() {
        dialog = new Dialog(getApplicationContext());
        dialog.setTitle("Thangcode.com");
        dialog.setContentView(R.layout.dialog_get_point);
        dialog.show();
    }

    // Thread sử dụng để Update trạng thái cho SeekBar.
    class UpdateSeekBarThread implements Runnable {

        public void run()  {
            int currentPosition = mediaPlayer.getCurrentPosition();
            int duration = mediaPlayer.getDuration();
            String currentPositionStr = millisecondsToString(currentPosition);
            String maxTimeString = millisecondsToString(duration);
//            textCurrentPosition.setText(currentPositionStr);

            seekBar.setProgress(currentPosition);

            // Ngừng thread 50 mili giây.
            threadHandler.postDelayed(this, 50);

            if(mediaPlayer.isPlaying() == false) {
//                answer.add()
                if(index < listPart1.size()) {
                    save(index);
                    editor.putString(String.valueOf(index), "A");
                    index++;
                    mediaPlayer.stop();
                    btnPrevious.setEnabled(true);
                    img_part1.setImageResource(listPart1.get(index).getImages());
                    playMediaPlayer(listPart1.get(index).getAudio());
                    setSeekBar();
                    clearCheck();
                } else {
                    Toast.makeText(practice_part1.this, "Finish practice part1", Toast.LENGTH_SHORT).show();
                }


            }
        }
    }

    public void save(final int index)
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioOptionA:
                        editor.putString(String.valueOf(index), "A");
                        // do operations specific to this selection
                        break;
                    case R.id.radioOptionB:
                        editor.putString(String.valueOf(index), "B");
                        // do operations specific to this selection
                        break;
                    case R.id.radioOptionC:
                        editor.putString(String.valueOf(index), "C");
                        // do operations specific to this selection
                        break;
                    case R.id.radioOptionD:
                        editor.putString(String.valueOf(index), "D");
                        // do operations specific to this selection
                        break;
                }
                totalAnswer++;
                editor.commit();
                Log.d("option", sharedPreferences.getString(String.valueOf(index), ""));

            }
        });

    }

    public void clearCheck()
    {
        radioGroup.clearCheck();
    }



}
