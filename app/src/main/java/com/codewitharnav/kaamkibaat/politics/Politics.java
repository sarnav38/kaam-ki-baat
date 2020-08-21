package com.codewitharnav.kaamkibaat.politics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codewitharnav.kaamkibaat.MainActivity;
import com.codewitharnav.kaamkibaat.R;
import com.codewitharnav.kaamkibaat.bio.Biography;
import com.codewitharnav.kaamkibaat.news.News;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Politics extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String url,mtitle,mcontent,mimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politics);

        // For toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kaam ki Baat - Politics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Nav coding here
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(Politics.this,drawerLayout,R.string.drw_op,R.string.drw_cl);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelected(item);
                return false;
            }
        });

        // firebase code
        mRecyclerView = findViewById(R.id.RV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Politics");
        reference.keepSynced(true);
    }
    // on other than menu item selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //  class for menuitem selected

    public void UserMenuSelected(MenuItem item){
        if (item.getItemId() == R.id.nav_home){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_politics){
            Intent intent = new Intent(this,Politics.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_news){
            Intent intent = new Intent(this, News.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_bio){
            Intent intent = new Intent(this, Biography.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_vid){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC3dzH7JgfIzm2na8QhIIscg"));
            try {
                Politics.this.startActivity(webIntent);
            } catch (ActivityNotFoundException ignored) {
            }
        }
        else if (item.getItemId() == R.id.exit){
//            System.exit(1);
            this.finishAffinity();
        }
    }

    // on Start render recycler view
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Pmember> options =
                new FirebaseRecyclerOptions.Builder<Pmember>()
                        .setQuery(reference, Pmember.class)
                        .build();


        FirebaseRecyclerAdapter<Pmember, PviewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Pmember, PviewHolder>(options) {


                    @Override
                    protected void onBindViewHolder(@NonNull PviewHolder viewHandler, int position, @NonNull Pmember member) {
                        viewHandler.Psetdetails(getApplication(), member.getTitle(), member.getContent(), member.getImage_url());

                        viewHandler.setOnClicklistener(new PviewHolder.ClickListener() {
                            @Override
                            public void onItemclick(View view, int position) {
                                mtitle = getItem(position).getTitle();
                                mcontent = getItem(position).getContent();
                                mimage = getItem(position).getImage_url();

                                Intent intent = new Intent(Politics.this, PoliticsA2.class);
                                intent.putExtra("title", mtitle);
                                intent.putExtra("content", mcontent);
                                intent.putExtra("image", mimage);
                                startActivity(intent);
                            }
                            @Override
                            public void onItemLongclick(View view, int position) {
                            }
                        });
                    }
                    @NonNull
                    @Override
                    public PviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.politics_row, parent, false);
                        return new PviewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}