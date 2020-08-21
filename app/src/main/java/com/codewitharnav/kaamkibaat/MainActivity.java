package com.codewitharnav.kaamkibaat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import com.codewitharnav.kaamkibaat.bio.Biography;
import com.codewitharnav.kaamkibaat.news.News;
import com.codewitharnav.kaamkibaat.politics.Politics;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    CardView btn_P,btn_N,btn_B,btn_V;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Nav coding here
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,R.string.drw_op,R.string.drw_cl);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelected(item);
                return false;
            }
        });
        btn_P =findViewById(R.id.cardView1);
        btn_N =findViewById(R.id.cardView2);
        btn_B =findViewById(R.id.cardView);
        btn_V =findViewById(R.id.cardView3);

        // render video button to Kaam ki baat channel
        btn_V.setOnClickListener(view -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
            try {
                MainActivity.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        });
        // render to politics pages.
        btn_P.setOnClickListener(view -> {

            Intent Politics_page = new Intent(MainActivity.this, Politics.class);
            startActivity(Politics_page);
        });
        // render to News pages.
        btn_N.setOnClickListener(view -> {
            Intent News_page = new Intent(MainActivity.this, News.class);
            startActivity(News_page);

        });
        // render to Biography pages.
        btn_B.setOnClickListener(view -> {
            Intent Bio_page = new Intent(MainActivity.this, Biography.class);
            startActivity(Bio_page);

        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void UserMenuSelected(MenuItem item){
        if (item.getItemId() == R.id.nav_home){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_politics){
            Intent intent = new Intent(this,Politics.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_news){
            Intent intent = new Intent(this,News.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_bio){
            Intent intent = new Intent(this,Biography.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_vid){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
            try {
                MainActivity.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        }

        else if (item.getItemId() == R.id.exit){
//            System.exit(1);
            this.finishAffinity();
        }

    }

}