package com.example.ric_2.javastore.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ric_2.javastore.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ImageView img_articulo;
    TextView txt_menu_name;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        img_articulo=(ImageView)itemView.findViewById(R.id.img_articulo);
        txt_menu_name=(TextView)itemView.findViewById(R.id.txt_menu_name);
    }
}
