package com.toeic.activity.lesson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.toeic.R;
import com.toeic.model.part1;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class practice_part1 extends AppCompatActivity {

    private Button start, pause, next;
    private ImageView img_part1;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private Handler threadHandler = new Handler();
    private TextView textMaxTime;
    private TextView textCurrentPosition;
    private static TextView txtQuestion;
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

    private static String[] listAnwer;
    private static int totalAnswer = 0;


    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
        index = 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.pause();
        index = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_part1);

        initPart1();
        img_part1 = (ImageView) findViewById(R.id.img_part1);

        radioGroup = findViewById(R.id.radioGroup);
        radioOptionA = findViewById(R.id.radioOptionA);
        radioOptionB = findViewById(R.id.radioOptionB);
        radioOptionC = findViewById(R.id.radioOptionC);
        radioOptionD = findViewById(R.id.radioOptionD);
        txtQuestion = findViewById(R.id.question);
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

//        playMediaPlayer(listPart1.get(0).getAudio());
//        this.mediaPlayer.start();
    }

    private void initPart1() {
        listAnwer = new String[100];
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
        playMediaPlayer(listPart1.get(0).getAudio());
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



    public void submit(View view) {
        scope();
    }

    public int getPoint() {
        int scope = 0;
        for (int i = 0; i < listPart1.size(); i++) {
            if (listAnwer[i] != null) {
                totalAnswer++;
                if (listPart1.get(i).getAnswer().equals(listAnwer[i])) {
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

    public void listQuestion(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("List question");
        String[] question = {"Question 1",
                "Question 2",
                "Question 3",
                "Question 4",
                "Question 5",
                "Question 6",
                "Question 7",
                "Question 8",
                "Question 9",
                "Question 10"};
        builder.setItems(question, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        index=0;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 1:");
                        img_part1.setImageResource(listPart1.get(0).getImages());
                        playMediaPlayer(listPart1.get(0).getAudio());
                        setSeekBar();
                        break;
                    case 1:
                        index = 1;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 2:");
                        img_part1.setImageResource(listPart1.get(1).getImages());
                        playMediaPlayer(listPart1.get(1).getAudio());
                        setSeekBar();
                        break;
                    case 2:
                        index = 2;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 3:");
                        img_part1.setImageResource(listPart1.get(2).getImages());
                        playMediaPlayer(listPart1.get(2).getAudio());
                        setSeekBar();
                        break;
                    case 3:
                        index = 3;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 4:");
                        img_part1.setImageResource(listPart1.get(3).getImages());
                        playMediaPlayer(listPart1.get(3).getAudio());
                        setSeekBar();
                        break;
                    case 4:
                        index = 4;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 5:");
                        img_part1.setImageResource(listPart1.get(4).getImages());
                        playMediaPlayer(listPart1.get(4).getAudio());
                        setSeekBar();
                        break;
                    case 5:
                        index = 5;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 6:");
                        img_part1.setImageResource(listPart1.get(5).getImages());
                        playMediaPlayer(listPart1.get(5).getAudio());
                        setSeekBar();
                        break;
                    case 6:
                        index = 6;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 7:");
                        img_part1.setImageResource(listPart1.get(6).getImages());
                        playMediaPlayer(listPart1.get(6).getAudio());
                        setSeekBar();
                        break;
                    case 7:
                        index = 7;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 8:");
                        img_part1.setImageResource(listPart1.get(7).getImages());
                        playMediaPlayer(listPart1.get(7).getAudio());
                        setSeekBar();
                        break;
                    case 8:
                        index = 8;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 9:");
                        img_part1.setImageResource(listPart1.get(8).getImages());
                        playMediaPlayer(listPart1.get(8).getAudio());
                        setSeekBar();
                        break;
                    case 9:
                        index = 9;
                        mediaPlayer.stop();
                        txtQuestion.setText("Quesiton 10:");
                        img_part1.setImageResource(listPart1.get(9).getImages());
                        playMediaPlayer(listPart1.get(9).getAudio());
                        setSeekBar();
                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
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
                if(index < 10) {
                    save();
                    txtQuestion.setText("Quesiton "+(index+1)+":");
                    mediaPlayer.stop();
                    img_part1.setImageResource(listPart1.get(index).getImages());
                    playMediaPlayer(listPart1.get(index).getAudio());
                    setSeekBar();
                    clearCheck();
                    index++;
                } else {
                    Toast.makeText(practice_part1.this, "Finish practice part1", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void save()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioOptionA:
                        listAnwer[index] = "A";
//                        editor.putString(String.valueOf(index), "A");
                        // do operations specific to this selection
                        break;
                    case R.id.radioOptionB:
                        listAnwer[index] = "B";
//                        editor.putString(String.valueOf(index), "B");
                        // do operations specific to this selection
                        break;
                    case R.id.radioOptionC:
                        listAnwer[index] = "C";
//                        editor.putString(String.valueOf(index), "C");
                        // do operations specific to this selection
                        break;
                    case R.id.radioOptionD:
                        listAnwer[index] = "D";
//                        editor.putString(String.valueOf(index), "D");
                        // do operations specific to this selection
                        break;
                }
                totalAnswer++;
                editor.commit();
            }
        });

    }

    public void clearCheck()
    {
        radioGroup.clearCheck();
    }


}
