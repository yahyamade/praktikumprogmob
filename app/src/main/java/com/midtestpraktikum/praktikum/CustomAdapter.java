package com.midtestpraktikum.praktikum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList mhs_id, mhs_nama, mhs_email;

    CustomAdapter(Context context,
                  ArrayList mhs_id, ArrayList mhs_nama, ArrayList mhs_email){
        this.context = context;
        this.mhs_id = mhs_id;
        this.mhs_nama = mhs_nama;
        this.mhs_email = mhs_email;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvidmhs.setText(String.valueOf(mhs_id.get(position)));
        holder.tvnamamhs.setText(String.valueOf(mhs_nama.get(position)));
        holder.tvemailmhs.setText(String.valueOf(mhs_email.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, update_data.class);
                intent.putExtra("id", String.valueOf(mhs_id.get(position)));
                intent.putExtra("nama", String.valueOf(mhs_nama.get(position)));
                intent.putExtra("email", String.valueOf(mhs_email.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mhs_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvidmhs, tvnamamhs, tvemailmhs;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvidmhs = itemView.findViewById(R.id.txtidmhs);
            tvnamamhs = itemView.findViewById(R.id.txtnamamhs);
            tvemailmhs = itemView.findViewById(R.id.txtemailmhs);
            mainLayout = itemView.findViewById(R.id.mainlayout);
        }
    }
}
