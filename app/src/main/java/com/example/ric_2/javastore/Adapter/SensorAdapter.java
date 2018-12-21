package com.example.ric_2.javastore.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ric_2.javastore.Interface.ItemClickListener;
import com.example.ric_2.javastore.Model.Sensor;
import com.example.ric_2.javastore.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorViewHolder> {

    Context context;
    List<Sensor> sensorList;

    public SensorAdapter(Context context, List<Sensor> sensorList) {
        this.context = context;
        this.sensorList = sensorList;
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=LayoutInflater.from(context).inflate(R.layout.sensor_item_layout,null);
        return new SensorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder sensorViewHolder, int i) {
        sensorViewHolder.txt_price.setText(new StringBuilder("$").append(sensorList.get(i).getPrice().toString()));
        sensorViewHolder.txt_sensor_name.setText(sensorList.get(i).getName());

        Picasso.with(context)
                .load(sensorList.get(i).getLink())
                .into(sensorViewHolder.img_articulo);


        sensorViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }
}
