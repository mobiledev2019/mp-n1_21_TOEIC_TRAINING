package com.toeic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.toeic.activity.part2;
import com.toeic.activity.words;
import com.toeic.adapter.MainAdpater;
import com.toeic.config.Item;
import com.toeic.fragment.FragmentHome;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity 
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Item> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        int id = menuItem.getItemId();
//                        Toast.makeText(getApplication(), "abc", Toast.LENGTH_LONG).show();
//                        Log.d("id", String.valueOf(id));
//                        return true;
//                    }
//                });

        initWidget();

    }

    private void initWidget() {
        listItem = new ArrayList<>();
        listItem.add(new Item("Part 1: Photo", R.drawable.ic_part1));
        listItem.add(new Item("Part 2: Question-Response", R.drawable.ic_part2));
        listItem.add(new Item("Part 3: Short conversation", R.drawable.icon_part3));
        listItem.add(new Item("Part 4: Short talk", R.drawable.ic_part4));
        listItem.add(new Item("Part 5: Incomplete Sentence", R.drawable.ic_part5));
        listItem.add(new Item("Part 6: Text completion", R.drawable.ic_part6));
        listItem.add(new Item("Part 7: Reading", R.drawable.ic_part7));
        RecyclerView myrv  = (RecyclerView) findViewById(R.id.recycleview_id);
        MainAdpater myAdapter = new MainAdpater(this, listItem);

        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(myAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d("id", String.valueOf(id));
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
//            Toast.makeText(MainActivity.this, "thuy an cut", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.word) {
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clickWords(View view) {
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(getApplicationContext(), words.class);
        this.startActivity(intent1);
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        FragmentHome fragmentHome = new FragmentHome();
//        fragmentTransaction.replace(R.id.content,fragmentHome);
//        fragmentTransaction.commit();
    }
}
