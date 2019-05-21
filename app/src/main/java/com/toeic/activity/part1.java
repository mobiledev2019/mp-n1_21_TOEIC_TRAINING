package com.toeic.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.toeic.R;
import com.toeic.adapter.LessonAdpater;
import com.toeic.config.Lesson;
import com.toeic.model.Unit;

import java.util.ArrayList;
import java.util.List;

public class part1 extends AppCompatActivity {

    List<Lesson> lessonList;
    List<Unit> unitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        init();
    }


    private void init() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Part 1: Photo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        unitList = new ArrayList<>();
        unitList.add(new Unit(0, R.drawable.img_unit1, "Unit 1: Luyện nghe và xem ảnh về người - 1", "Bạn chưa học bài này"));
        unitList.add(new Unit(1, R.drawable.img_unit1, "Unit 2: Luyện nghe và xem ảnh về người - 2", "Bạn chưa học bài này"));
        unitList.add(new Unit(2, R.drawable.img_unit1, "Unit 3: Luyện nghe và xem ảnh về người - 3", "Bạn chưa học bài này"));
        unitList.add(new Unit(3, R.drawable.img_vat, "Unit 3: Luyện nghe và xem ảnh về vật - 1", "Bạn chưa học bài này"));
        unitList.add(new Unit(4, R.drawable.img_vat, "Unit 3: Luyện nghe và xem ảnh về vật - 2", "Bạn chưa học bài này"));
        unitList.add(new Unit(5, R.drawable.img_vat, "Unit 3: Luyện nghe và xem ảnh về vật - 3", "Bạn chưa học bài này"));

        RecyclerView myrv  = (RecyclerView) findViewById(R.id.recycleview_id);
        LessonAdpater myAdapter = new LessonAdpater(this, unitList);

        myrv.setLayoutManager(new GridLayoutManager(this, 1));
        myrv.setAdapter(myAdapter);
    }


}
