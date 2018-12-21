package com.example.ric_2.javastore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ric_2.javastore.Interface.ItemClickListener;
import com.example.ric_2.javastore.Model.Category;
import com.example.ric_2.javastore.R;
import com.example.ric_2.javastore.SensorActivity;
import com.example.ric_2.javastore.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.menu_item_layout,null);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        //Carga imagen
        Picasso.with(context)
                .load(categories.get(position).getLink())
                .into(holder.img_articulo);
        holder.txt_menu_name.setText(categories.get(position).getName());

        //eventos
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Common.currentCategory=categories.get(position);

                //Activity sensores
                context.startActivity(new Intent(context,SensorActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
