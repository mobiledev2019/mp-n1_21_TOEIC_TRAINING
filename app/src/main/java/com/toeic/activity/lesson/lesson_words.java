package com.toeic.activity.lesson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.toeic.R;
import com.toeic.activity.part1;
import com.toeic.adapter.LessonAdpater;
import com.toeic.adapter.lessonWordAdpater;
import com.toeic.common.Common;
import com.toeic.model.Unit;
import com.toeic.model.Word;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class lesson_words extends AppCompatActivity {

    Common common;
    List<Word> listword ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_words);
        init();
        GetData(common.getWord());
    }

    private void init() {
        listword = new ArrayList<>();
        common = new Common();
    }
    private void GetData(String url) {
//        final ArrayList<Unit> list = new ArrayList<Unit>() ;
        listword = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(lesson_words.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(lesson_words.this, "abv", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                listword.add(new Word(jsonObject.getString("en"), jsonObject.getString("vn"))
                                );

                            } catch (JSONException e) {

                            }
                        }
                        Log.d("list", listword.toString());
                        RecyclerView myrv  = (RecyclerView) findViewById(R.id.recycleview_id);
                        lessonWordAdpater myAdapter = new lessonWordAdpater(getApplication(), listword);

                        myrv.setLayoutManager(new GridLayoutManager(getApplication(), 1));
                        myrv.setAdapter(myAdapter);
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(lesson_words.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
//        return list;
    }
}
