package com.valencia.oscar.w3d4_as1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.valencia.oscar.w3d4_ex1.R;

import java.io.IOException;
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
                userItem.getTitle()+ " "+
                        userItem.getFirstName()+ " "+
                        userItem.getLastName()+" "+
                        userItem.getEmail()+" "+
                        userItem.getCity()+" "+
                        userItem.getPhone()
        );
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream(new java.net.URL(userItem.getPicture()).openStream());
//            holder.tvPicture.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Log.d(LOG,"Binging item "+userItem.getFirstName()+" "+userItem.getLastName());


    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 :dataSet.size();
    }


}
