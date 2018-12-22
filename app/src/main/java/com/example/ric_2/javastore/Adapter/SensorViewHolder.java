package com.example.ric_2.javastore.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ric_2.javastore.Interface.ItemClickListener;
import com.example.ric_2.javastore.R;

public class SensorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img_articulo;
    TextView txt_sensor_name, txt_price;

    ItemClickListener itemClickListener;

    Button btn_add_car;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SensorViewHolder(@NonNull View itemView) {
        super(itemView);

        img_articulo=(ImageView)itemView.findViewById(R.id.img_articulo);
        txt_sensor_name=(TextView) itemView.findViewById(R.id.txt_sensor_name);
        txt_price=(TextView)itemView.findViewById(R.id.txt_sensor_price);
        btn_add_car=(Button)itemView.findViewById(R.id.btn_add_cart);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view);
    }
}
