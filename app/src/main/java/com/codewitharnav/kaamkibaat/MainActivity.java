package com.codewitharnav.kaamkibaat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codewitharnav.kaamkibaat.bio.Biography;
import com.codewitharnav.kaamkibaat.bio.BiographyA2;
import com.codewitharnav.kaamkibaat.bio.Bmember;
import com.codewitharnav.kaamkibaat.bio.BviewHolder;
import com.codewitharnav.kaamkibaat.latest.LatestA2;
import com.codewitharnav.kaamkibaat.latest.Lmember;
import com.codewitharnav.kaamkibaat.latest.LviewHolder;
import com.codewitharnav.kaamkibaat.news.News;
import com.codewitharnav.kaamkibaat.news.NewsA2;
import com.codewitharnav.kaamkibaat.news.Nmember;
import com.codewitharnav.kaamkibaat.news.NviewHolder;
import com.codewitharnav.kaamkibaat.politics.Politics;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    CardView btn_P,btn_N,btn_B,btn_V;
    ProgressDialog pd;
    NavigationView navigationView;
    RecyclerView mRecyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    String mtitle,mcontent,mimage;
    RecyclerView.LayoutManager manager;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aa);


        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // create progress dialog
        pd = new ProgressDialog(MainActivity.this);

        //Nav coding here
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,R.string.drw_op,R.string.drw_cl);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.navigation_view);

        btn_P =findViewById(R.id.cardView1);
        btn_N =findViewById(R.id.cardView2);
        btn_B =findViewById(R.id.cardView);
        btn_V =findViewById(R.id.cardView3);

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(item -> {
            UserMenuSelected(item);
            return false;
        });
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
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent Politics_page = new Intent(MainActivity.this, Politics.class);
            startActivity(Politics_page);
        });
        // render to News pages.
        btn_N.setOnClickListener(view -> {
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent News_page = new Intent(MainActivity.this, News.class);
            startActivity(News_page);

        });
        // render to Biography pages.
        btn_B.setOnClickListener(view -> {
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent Bio_page = new Intent(MainActivity.this, Biography.class);
            startActivity(Bio_page);
        });

        // for latest

        mRecyclerView = findViewById(R.id.recycler_latest);
        mRecyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,true);
        mRecyclerView.setLayoutManager(manager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Latest");
        reference.keepSynced(true);
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
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent intent = new Intent(this,Politics.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_news){
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            Intent intent = new Intent(this,News.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.nav_bio){
            pd.show();
            pd.setContentView(R.layout.pd_lo);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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
        else if (item.getItemId() == R.id.nav_share){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"open this app to study beutyfull post http:/play.google.com");
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent,"share via"));


            startActivity(intent);
        }

        else if (item.getItemId() == R.id.exit){
//            System.exit(1);
            this.finishAffinity();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        // latest Recyclerview Adapter
        FirebaseRecyclerOptions<Lmember> options =
                new FirebaseRecyclerOptions.Builder<Lmember>()
                        .setQuery(reference, Lmember.class)
                        .build();


        FirebaseRecyclerAdapter<Lmember, LviewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Lmember, LviewHolder>(options) {


                    @Override
                    protected void onBindViewHolder(@NonNull LviewHolder viewHandler, int position, @NonNull Lmember member) {
                        viewHandler.Lsetdetails(getApplication(), member.getTitle(), member.getContent(), member.getImage_url());

                        viewHandler.setOnClicklistener(new LviewHolder.ClickListener() {
                            @Override
                            public void onItemclick(View view, int position) {
                                mtitle = getItem(position).getTitle();
                                mcontent = getItem(position).getContent();
                                mimage = getItem(position).getImage_url();

                                Intent intent = new Intent(MainActivity.this, LatestA2.class);
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
                    public LviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.latest_items, parent, false);
                        return new LviewHolder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        pd.dismiss();
    }

    private void firebaseSearch2(String searchtext){
        String query = searchtext.toLowerCase();
        CardView cardView = findViewById(R.id.cardView);
        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        TextView latest = findViewById(R.id.textView7);
        cardView.setVisibility(View.GONE);
        cardView1.setVisibility(View.GONE);
        cardView2.setVisibility(View.GONE);
        cardView3.setVisibility(View.GONE);
        latest.setVisibility(View.GONE);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        Query firebaseQuery = firebaseDatabase.getReference("News").orderByChild("title").startAt(query).endAt(query + "\uf8ff");
            FirebaseRecyclerOptions<Nmember> options =
                    new FirebaseRecyclerOptions.Builder<Nmember>()
                            .setQuery(firebaseQuery, Nmember.class)
                            .build();
            FirebaseRecyclerAdapter<Nmember, NviewHolder> firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<Nmember, NviewHolder>(options) {


                        @Override
                        protected void onBindViewHolder(@NonNull NviewHolder viewHandler, int position, @NonNull Nmember member) {
                            viewHandler.Nsetdetails(getApplication(), member.getTitle(), member.getContent(), member.getImage_url());

                            viewHandler.setOnClicklistener(new NviewHolder.ClickListener() {
                                @Override
                                public void onItemclick(View view, int position) {
                                    mtitle = getItem(position).getTitle();
                                    mcontent = getItem(position).getContent();
                                    mimage = getItem(position).getImage_url();

                                    Intent intent = new Intent(MainActivity.this, NewsA2.class);
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
                        public NviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.news_row, parent, false);
                            return new NviewHolder(view);
                        }
                    };
            firebaseRecyclerAdapter.startListening();
            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private void firebaseSearch(String searchtext){
        String query = searchtext.toLowerCase();
        CardView cardView = findViewById(R.id.cardView);
        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        TextView latest = findViewById(R.id.textView7);
        cardView.setVisibility(View.GONE);
        cardView1.setVisibility(View.GONE);
        cardView2.setVisibility(View.GONE);
        cardView3.setVisibility(View.GONE);
        latest.setVisibility(View.GONE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);
            Query firebaseQuery2 = firebaseDatabase.getReference("Biography").orderByChild("title").startAt(query).endAt(query + "\uf8ff");

            FirebaseRecyclerOptions<Bmember> options =
                    new FirebaseRecyclerOptions.Builder<Bmember>()
                            .setQuery(firebaseQuery2,Bmember.class)
                            .build();
            FirebaseRecyclerAdapter<Bmember,BviewHolder> firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<Bmember, BviewHolder>(options) {


                        @Override
                        protected void onBindViewHolder(@NonNull BviewHolder viewHandler, int position, @NonNull Bmember member) {
                            viewHandler.Bsetdetails(getApplication(), member.getTitle(), member.getContent(), member.getImage_url());

                            viewHandler.setOnClicklistener(new BviewHolder.ClickListener() {
                                @Override
                                public void onItemclick(View view, int position) {
                                    mtitle = getItem(position).getTitle();
                                    mcontent = getItem(position).getContent();
                                    mimage = getItem(position).getImage_url();

                                    Intent intent = new Intent(MainActivity.this, BiographyA2.class);
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
                        public BviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.biography_row, parent, false);
                            return new BviewHolder(view);
                        }
                    };
            if (!firebaseQuery2.equals(query)){
                firebaseSearch2(query);
        }
            firebaseRecyclerAdapter.startListening();
            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_btn,menu);
        MenuItem item = menu.findItem(R.id.search_firebase);
        searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
//                firebaseSearch2(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
//                firebaseSearch2(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}