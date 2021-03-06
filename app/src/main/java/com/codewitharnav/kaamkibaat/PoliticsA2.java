package com.codewitharnav.kaamkibaat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

public class PoliticsA2 extends AppCompatActivity {
    TextView mtitle;
    ImageView mimage;
    TextView mcontent;
    private String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politics_a2);

        getSupportActionBar().setTitle("Kaam Ki Baat - Politics");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mtitle = findViewById(R.id.textView_P_A2_1);
        mimage = findViewById(R.id.imageView_P_A2_1);
        mcontent = findViewById(R.id.textView_P_A2_2);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("title");
        String content = intent.getExtras().getString("content");
        image =intent.getExtras().getString("image");

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(Html.fromHtml(content));
        Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(image).into(mimage);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}