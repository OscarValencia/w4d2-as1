package com.valencia.oscar.w4d2_as1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.request.RequestOptions;
import com.valencia.oscar.w3d4_ex1.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private static final String LOG = UserAdapter.class.getSimpleName()+"_TAG";
    private ArrayList<UserItem> dataSet;

    public UserAdapter(ArrayList<UserItem> dataSet) {
        this.dataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvUser;
        public ImageView tvPicture;
        public ViewHolder(View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvPicture = itemView.findViewById(R.id.tvPicture);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserItem userItem = dataSet.get(position);
        holder.tvUser.setText(
                        "Title: "+userItem.getTitle()+ "\n"+
                        "Subtitle: "+(userItem.getSubtitle()==null?"":userItem.getSubtitle())+"\n"+
                        "Authors: "+userItem.getAuthors()
        );

        RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_holder).error(R.drawable.ic_holder);
        Glide
                .with(holder.tvPicture.getContext())
                .load(userItem.getThumbnail())
                .apply(options)
                .into(holder.tvPicture);
        Log.d(LOG,"Binging item "+userItem.getId()+" "+userItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 :dataSet.size();
    }



}
