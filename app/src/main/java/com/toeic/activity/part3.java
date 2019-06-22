package com.toeic.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.toeic.R;
import com.toeic.adapter.LessonPart2Adapter;
import com.toeic.adapter.LessonPart3Adapter;
import com.toeic.common.Common;
import com.toeic.config.Lesson;
import com.toeic.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class part3 extends AppCompatActivity {
    String b = words.a;
    List<Lesson> lessonList;
    List<Unit> unitList;
    List listAvatar;
    Common common ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3);
        init();
    }

    private void init() {
        common = new Common();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Part 3: ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GetData(common.getUnit());
    }

    private void GetData(String url) {
//        final ArrayList<Unit> list = new ArrayList<Unit>() ;
        unitList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(part3.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
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
                                LessonPart3Adapter myAdapter = new LessonPart3Adapter(getApplication(), unitList);

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
                        Toast.makeText(part3.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
//        return list;
    }
}
