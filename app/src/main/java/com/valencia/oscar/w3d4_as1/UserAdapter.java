package com.valencia.oscar.w3d4_as1;

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

import com.valencia.oscar.w3d4_ex1.R;

import java.io.IOException;
import java.io.InputStream;
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
                        userItem.getEmail()+"\n "+
                        userItem.getCity()+"\n "+
                        userItem.getPhone()
        );
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        new DownloadImageTask( holder.tvPicture)
                .execute(userItem.getPicture());

        Log.d(LOG,"Binging item "+userItem.getFirstName()+" "+userItem.getLastName());
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 :dataSet.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
