package com.example.ric_2.javastore.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
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
    public void onBindViewHolder(@NonNull SensorViewHolder sensorViewHolder, final int i) {
        sensorViewHolder.txt_price.setText(new StringBuilder("$").append(sensorList.get(i).getPrice().toString()));
        sensorViewHolder.txt_sensor_name.setText(sensorList.get(i).getName());

        sensorViewHolder.btn_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddToCartDialog(i);
            }
        });

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

    private void showAddToCartDialog(int i) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                                    .inflate(R.layout.add_to_cart,null);

        //Imagen
        ImageView img_article_dialog=(ImageView)itemView.findViewById(R.id.img_cart_article);
        ElegantNumberButton txt_count=(ElegantNumberButton)itemView.findViewById(R.id.txt_count);
        TextView txt_article_dialog=(TextView)itemView.findViewById(R.id.txt_cart_article_name);

        EditText edt_comentario=(EditText)itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_1=(RadioButton)itemView.findViewById(R.id.rdi_1);
        RadioButton rdi_2=(RadioButton)itemView.findViewById(R.id.rdi_2);
        RadioButton rdi_3=(RadioButton)itemView.findViewById(R.id.rdi_3);
        RadioButton rdi_4=(RadioButton)itemView.findViewById(R.id.rdi_4);
        RadioButton rdi_5=(RadioButton)itemView.findViewById(R.id.rdi_5);
        RadioButton rdi_6=(RadioButton)itemView.findViewById(R.id.rdi_6);
        RadioButton rdi_7=(RadioButton)itemView.findViewById(R.id.rdi_7);

        Picasso.with(context)
                .load(sensorList.get(i).getLink())
                .into(img_article_dialog);
        txt_article_dialog.setText(sensorList.get(i).getName());

        builder.setView(itemView);
        builder.setNegativeButton("Añadir al Carrito", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }
}
