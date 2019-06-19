package com.toeic.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toeic.R;
import com.toeic.adapter.LessonAdpater;
import com.toeic.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class words extends AppCompatActivity {
    public static String a = "adasdasdasd";
    List<Unit> unitList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("1000 từ vựng TOEIC");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        unitList = new ArrayList<>();
        unitList.add(new Unit(0, R.drawable.img_unit1, "Chủ đề 1: Contracts", "Bạn chưa học bài này"));
        unitList.add(new Unit(1, R.drawable.img_unit1, "Chủ đề 2: Marketing", "Bạn chưa học bài này"));
        unitList.add(new Unit(2, R.drawable.img_unit1, "Chủ đề 3: Warranties", "Bạn chưa học bài này"));
        unitList.add(new Unit(3, R.drawable.img_vat, "Chủ đề 4: Busiess", "Bạn chưa học bài này"));
        unitList.add(new Unit(4, R.drawable.img_vat, "Chủ đề 5: Conferences", "Bạn chưa học bài này"));
        unitList.add(new Unit(5, R.drawable.img_vat, "Chủ đề 6: Computers", "Bạn chưa học bài này"));

        RecyclerView myrv  = (RecyclerView) findViewById(R.id.recycleview_id);
        LessonAdpater myAdapter = new LessonAdpater(this, unitList);

        myrv.setLayoutManager(new GridLayoutManager(this, 1));
        myrv.setAdapter(myAdapter);
    }
}
