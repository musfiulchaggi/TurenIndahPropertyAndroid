package com.example.turenindahproperty;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<data_property> daftarProperty;
    private Context context;

    public ListAdapter(ArrayList<data_property> properties, Context ctx){
        daftarProperty = properties;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvHarga;
        TextView tvLbLt;
        TextView tvAlamat;
        ImageView images;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.textTitle);
            tvHarga = v.findViewById(R.id.textSubtitle);
            tvLbLt = v.findViewById(R.id.textLbLt);
            tvAlamat = v.findViewById(R.id.alamat);
            images = v.findViewById(R.id.image);
            cvMain = (CardView) v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */

        System.out.println("BARANG DATA one by one "+position+daftarProperty.size());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
                context.startActivity(Detail.getActIntent((Activity) context).putExtra("data", daftarProperty.get(position)));
            }
        });
        holder.tvTitle.setText(daftarProperty.get(position).getName());
        holder.tvAlamat.setText(daftarProperty.get(position).getAlamat());
        holder.tvLbLt.setText("LB "+daftarProperty.get(position).getLb()+" / LT "+daftarProperty.get(position).getLt());
        Glide.with(context).load(daftarProperty.get(position).getImage()).into(holder.images);
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarProperty.size();
    }
}
