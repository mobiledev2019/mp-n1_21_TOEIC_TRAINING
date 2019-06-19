package com.toeic.activity.lesson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.toeic.OnEventListener;
import com.toeic.R;
import com.toeic.activity.part1;
import com.toeic.adapter.LessonAdpater;
import com.toeic.api.getApi;
import com.toeic.common.Common;
import com.toeic.model.Part1;
import com.toeic.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class lesson_part1 extends AppCompatActivity {

    Button btnList, btnView, btnNext, btnPrev, btnExit;
    ImageView imgPicture;
    SeekBar sbAudio;
    RadioButton rbOptionA, rbOptionB, rbOptionC, rbOptionD;
    AlertDialog.Builder dialog;
    String[] question = {"question 1", "question 2"};
    static ArrayList<Part1> part1ArrayList;
    Common common;
    getApi getApi;
    private MediaPlayer mediaPlayer;
    private Handler threadHandler = new Handler();
    private TextView textMaxTime;
    private TextView textCurrentPosition;
    private SeekBar seekBar;

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
//        this.textCurrentPosition = (TextView)this.findViewById(R.id.textView_currentPosion);
//        this.textMaxTime=(TextView) this.findViewById(R.id.textView_maxTime);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.seekBar.setClickable(false);

        part1ArrayList = new ArrayList<>();
        common = new Common();
        getApi getApi = new getApi(common.getPart1(), getApplicationContext(), new OnEventListener() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                Toast.makeText(lesson_part1.this,   jsonArray.toString() , Toast.LENGTH_SHORT).show();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = jsonArray.getJSONObject(i);
                        part1ArrayList.add(new Part1(
                                jsonObject.getInt("id"),
                                jsonObject.getInt("test_id"),
                                jsonObject.getString("picture"),
                                "ex"+i,
                                jsonObject.getString("media"),
                                jsonObject.getString("answer")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(lesson_part1.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
//        part1ArrayList = GetData("http://192.168.31.113/laravel/public/part1");
//        Toast.makeText(this, part1ArrayList.size(), Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, part1ArrayList.toString(), Toast.LENGTH_SHORT).show();
    }

    public void showDialog() {
        

    }
//
//    private void initPart1() {
//        part1ArrayList = new ArrayList();
//        part1ArrayList.add(new part1("1", "1", R.drawable.img_part12, "ex1", R.raw.part1, "B"));
//        part1ArrayList.add(new part1("2", "1", R.drawable.img_part1, "ex2", R.raw.part1, "C"));
//        part1ArrayList.add(new part1("3", "1", R.drawable.img_part12, "ex3", R.raw.part1, "D"));
//    }


    // play video
    private void playMediaPlayer(int uri) {
        //creating media player

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer = MediaPlayer.create(this, uri);
//            mp.prepare();
//        mediaPlayer.start();
    }



//    private ArrayList GetData(String url) {
////        final ArrayList<Unit> list = new ArrayList<Unit>() ;
//        part1ArrayList = new ArrayList<>();
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        for (int i = 0; i < response.length(); i++) {
//                            JSONObject jsonObject = null;
//                            try {
//                                jsonObject = response.getJSONObject(i);
//                                part1ArrayList.add(new Part1(
//                                        jsonObject.getInt("id"),
//                                        jsonObject.getInt("test_id"),
//                                        jsonObject.getString("picture"),
//                                        "ex"+i,
//                                        jsonObject.getString("media"),
//                                        jsonObject.getString("answer")
//                                ));
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        Toast.makeText(lesson_part1.this, "oka"+part1ArrayList.size(), Toast.LENGTH_SHORT).show();
//
//                    }
//                },
//                new Response.ErrorListener(){
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
////        Toast.makeText(getApplicationContext(), part1ArrayList.toString(), Toast.LENGTH_SHORT).show();
//
//
//        requestQueue.add(jsonArrayRequest);
//        return part1ArrayList;
//    }

    public void btnExit(View view) {

    }

    public void btnList(View view) {

        showDialog();
    }
}
