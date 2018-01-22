package com.sdsmdg.harjot.MusicDNA.fragments.MenuFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdsmdg.harjot.MusicDNA.R;
import com.sdsmdg.harjot.MusicDNA.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PC on 22/01/2018.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.MyViewHolder> {

    private List<Product> localTracks;
    private Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView art;
        TextView title, artist , price;

        public MyViewHolder(View view) {
            super(view);
            art = (ImageView) view.findViewById(R.id.img_2);
            title = (TextView) view.findViewById(R.id.title_2);
            artist = (TextView) view.findViewById(R.id.url_2);
            price = (TextView)view.findViewById(R.id.price);
        }
    }

    public ProductRecyclerAdapter(List<Product> localTracks, Context ctx) {
        this.localTracks = localTracks;
        this.ctx = ctx;
    }

    @Override
    public ProductRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_2, parent, false);

        return new ProductRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductRecyclerAdapter.MyViewHolder holder, int position) {
        Product track = localTracks.get(position);
        holder.title.setText(track.getName());
        holder.artist.setText(track.getDetails());
        holder.price.setText(track.getPrice()+"$");
        Picasso.with(ctx).load(track.getImgUrl()).into(holder.art);

    }

    @Override
    public int getItemCount() {
        return localTracks.size();
    }

    public static Bitmap getAlbumArt(String path) {
        android.media.MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(path);
        Bitmap bitmap = null;

        byte[] data = mmr.getEmbeddedPicture();
        if (data != null) {
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            return bitmap;
        } else {
            return null;
        }
    }
}
