package com.codewitharnav.kaamkibaat;

import android.app.Application;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;

public class NviewHolder extends RecyclerView.ViewHolder {
    View view;

    public NviewHolder(@NonNull View itemView) {
        super(itemView);
        view =itemView;

        itemView.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mClicklistener.onItemclick(view,getAdapterPosition());
            }

        });
        itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemLongclick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void Nsetdetails(Application application, String title, String content, String image_url) {
        TextView mtitle = view.findViewById(R.id.textView_N_2);
        TextView mcontent = view.findViewById(R.id.textView_N_3);
        ImageView mimage = view.findViewById(R.id.imageView_N);

        mtitle.setText(Html.fromHtml(title));
        mcontent.setText(content);
        Picasso.get().load(image_url).networkPolicy(NetworkPolicy.OFFLINE).into(mimage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(image_url).into(mimage);
            }
        });
    }

    private NviewHolder.ClickListener mClicklistener;

    public interface  ClickListener{
        void onItemclick(View view, int position);
        void onItemLongclick(View view, int position);

    }
    public  void setOnClicklistener(NviewHolder.ClickListener clicklistener){
        mClicklistener = clicklistener;
    }
}


