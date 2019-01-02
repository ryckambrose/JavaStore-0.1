package com.example.ric_2.javastore.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ric_2.javastore.Database.ModelDB.Cart;
import com.example.ric_2.javastore.Interface.ItemClickListener;
import com.example.ric_2.javastore.Model.Sensor;
import com.example.ric_2.javastore.R;
import com.example.ric_2.javastore.Utils.Common;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.security.spec.ECField;
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
        sensorViewHolder.txt_price.setText(new StringBuilder("$").append(sensorList.get(i).Price).toString());
        sensorViewHolder.txt_sensor_name.setText(sensorList.get(i).Name);

        sensorViewHolder.btn_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddToCartDialog(i);
            }
        });

        Picasso.with(context)
                .load(sensorList.get(i).Link)
                .into(sensorViewHolder.img_articulo);


        sensorViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Seleccionado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAddToCartDialog(final int i) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                                    .inflate(R.layout.add_to_cart,null);

        //Imagen
        ImageView img_article_dialog=(ImageView)itemView.findViewById(R.id.img_cart_article);
        final ElegantNumberButton txt_count=(ElegantNumberButton)itemView.findViewById(R.id.txt_count);
        TextView txt_article_dialog=(TextView)itemView.findViewById(R.id.txt_cart_article_name);

        EditText edt_comentario=(EditText)itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_1=(RadioButton)itemView.findViewById(R.id.rdi_1);
        RadioButton rdi_2=(RadioButton)itemView.findViewById(R.id.rdi_2);

        //Validaciones nivel 1
        rdi_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Common.nivel_1 = 0;
                }
            }
        });

        rdi_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Common.nivel_1 = 0;
                }
            }
        });

        RadioButton rdi_3=(RadioButton)itemView.findViewById(R.id.rdi_3);
        RadioButton rdi_4=(RadioButton)itemView.findViewById(R.id.rdi_4);
        RadioButton rdi_5=(RadioButton)itemView.findViewById(R.id.rdi_5);
        RadioButton rdi_6=(RadioButton)itemView.findViewById(R.id.rdi_6);
        RadioButton rdi_7=(RadioButton)itemView.findViewById(R.id.rdi_7);

        rdi_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Common.nivel_2 = 0;
                }
            }
        });

        rdi_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Common.nivel_2 = 0;
                }
            }
        });

        rdi_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Common.nivel_2 = 0;
                }
            }
        });

        rdi_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Common.nivel_2 = 0;
                }
            }
        });

        rdi_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if(isCheked) {
                    Common.nivel_2 = 0;
                }
            }
        });

        RecyclerView recycler_topping=(RecyclerView)itemView.findViewById(R.id.recycler_topping);
        recycler_topping.setLayoutManager(new LinearLayoutManager(context));
        recycler_topping.setHasFixedSize(true);

        MultiChoiceAdapter adapter=new MultiChoiceAdapter(context,Common.toppingList);
        recycler_topping.setAdapter(adapter);

        //Coloca datos
        Picasso.with(context)
                .load(sensorList.get(i).Link)
                .into(img_article_dialog);
        txt_article_dialog.setText(sensorList.get(i).Name);

        builder.setView(itemView);
        builder.setNegativeButton("Añadir al Carrito", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {

                if(Common.nivel_1==-1)
                {
                    Toast.makeText(context, "Escoge una opcion nivel 1", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Common.nivel_2==-1)
                {
                    Toast.makeText(context, "Escoge una opcion nivel 2", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if(Common.nivel_3==-1)
                {
                    Toast.makeText(context, "Escoge una opcion nivel 3", Toast.LENGTH_SHORT).show();
                    return;
                }*/

                showConfirmDialog(i,txt_count.getNumber());
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void showConfirmDialog(int position, final String number) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View itemView=LayoutInflater.from(context)
                .inflate(R.layout.confirm_add_to_cart_layout,null);

        //vista
        ImageView img_article_dialog=(ImageView)itemView.findViewById(R.id.img_articulo);
        final TextView txt_article_dialog=(TextView)itemView.findViewById(R.id.txt_cart_article_name);
        TextView txt_article_price=(TextView)itemView.findViewById(R.id.txt_cart_article_price);
        TextView txt_nivel_1=(TextView)itemView.findViewById(R.id.txt_nivel_1);
        TextView txt_nivel_2=(TextView)itemView.findViewById(R.id.txt_nivel_2);
        final TextView txt_nivel_3=(TextView)itemView.findViewById(R.id.txt_character_extra);

        //Coloca datos
        Picasso.with(context).load(sensorList.get(position).Link).into(img_article_dialog);
        txt_article_dialog.setText(new StringBuilder(sensorList.get(position).Name).append("x")
        .append(number)
        .append(Common.nivel_1==0?"Opcion 1":"Opcion 2").toString());

        txt_nivel_1.setText(new StringBuilder("Nivel 1:").append(Common.nivel_1).append("X").toString());
        txt_nivel_2.setText(new StringBuilder("Nivel 2:").append(Common.nivel_2).append("Y").toString());

        double price=(Double.parseDouble(sensorList.get(position).Price)*Double.parseDouble(number))+Common.toppingPrice;

        if(Common.nivel_1==1)
            price+=3.0;

        txt_article_price.setText(new StringBuilder("$").append(price));

        StringBuilder final_comment=new StringBuilder("");
        for(String line:Common.toppingAdded)
            final_comment.append(line).append(price);

        txt_nivel_3.setText(final_comment);

        final double finalPrice = price;
        builder.setNegativeButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Agrega a SQLite
                dialogInterface.dismiss();

                //Crea un nuevo carrito
                try {

                    Cart cartItem =new Cart();
                    cartItem.name=txt_article_dialog.getText().toString();
                    cartItem.amount=Integer.parseInt(number);
                    cartItem.nivel1=Common.nivel_1;
                    cartItem.nivel2=Common.nivel_2;
                    cartItem.price= finalPrice;
                    cartItem.extra=txt_nivel_3.getText().toString();

                    //agrega a BD
                    Common.cartRepository.insertToCart(cartItem);
                    Log.d("RIC_DEBUG",new Gson().toJson(cartItem));
                    Toast.makeText(context,"Articulo agregado al carrito", Toast.LENGTH_SHORT).show();

                }catch (Exception ex){
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setView(itemView);
        builder.show();

    }

    @Override
    public int getItemCount() {
        return sensorList.size();
    }
}
