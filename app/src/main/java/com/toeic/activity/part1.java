package com.toeic.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.toeic.R;
import com.toeic.adapter.LessonAdpater;
import com.toeic.common.Common;
import com.toeic.config.Lesson;
import com.toeic.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import com.toeic.activity.words;

public class part1 extends AppCompatActivity {
    String b = words.a;
    List<Lesson> lessonList;
    List<Unit> unitList;
    List listAvatar;
    Common common ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        init();
    }


    private void init() {
        common = new Common();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Part 1: Photo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GetData("http://192.168.0.101/laravel/public/test");
//        unitList = new ArrayList<>();
//        unitList.add(new Unit(0, R.drawable.img_unit1, "Unit 1: Luyện nghe và xem ảnh về người - 1", "Bạn chưa học bài này"));
//        unitList.add(new Unit(1, R.drawable.img_unit1, "Unit 2: Luyện nghe và xem ảnh về người - 2", "Bạn chưa học bài này"));
//        unitList.add(new Unit(2, R.drawable.img_unit1, "Unit 3: Luyện nghe và xem ảnh về người - 3", "Bạn chưa học bài này"));
//        unitList.add(new Unit(3, R.drawable.img_vat, "Unit 3: Luyện nghe và xem ảnh về vật - 1", "Bạn chưa học bài này"));
//        unitList.add(new Unit(4, R.drawable.img_vat, "Unit 3: Luyện nghe và xem ảnh về vật - 2", "Bạn chưa học bài này"));
//        unitList.add(new Unit(5, R.drawable.img_vat, "Unit 3: Luyện nghe và xem ảnh về vật - 3", "Bạn chưa học bài này"));
//
//        Toast.makeText(this, unitList.toString(), Toast.LENGTH_SHORT).show();
//        RecyclerView myrv  = (RecyclerView) findViewById(R.id.recycleview_id);
//        LessonAdpater myAdapter = new LessonAdpater(this, unitList);
//
//        myrv.setLayoutManager(new GridLayoutManager(this, 1));
//        myrv.setAdapter(myAdapter);
//        GetData("http://10.30.0.174/test");
    }

    private void GetData(String url) {
//        final ArrayList<Unit> list = new ArrayList<Unit>() ;
        unitList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(part1.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Method.GET, url, null,
            new Response.Listener<JSONArray>() {

                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            unitList.add(new Unit(
                                    jsonObject.getInt("id"),
                                    R.drawable.img_unit1,
                                    jsonObject.getString("title"),
                                    jsonObject.getInt("status"))
                            );
                            RecyclerView myrv  = (RecyclerView) findViewById(R.id.recycleview_id);
                            LessonAdpater myAdapter = new LessonAdpater(getApplication(), unitList);

                            myrv.setLayoutManager(new GridLayoutManager(getApplication(), 1));
                            myrv.setAdapter(myAdapter);
                        } catch (JSONException e) {

                        }
                    }
                }
            },
            new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(part1.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        requestQueue.add(jsonArrayRequest);
//        return list;
    }

}
