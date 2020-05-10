package com.example.guizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class categoryAdepter extends RecyclerView.Adapter<categoryAdepter.Viewholder> {
    public categoryAdepter(List<categoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    private List<categoryModel> categoryModelList;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
    return  new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
holder.setData(categoryModelList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder
    {
        private TextView title;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv);
        }
        private void setData(final String title)
        {
            this.title.setText(title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setintent=new Intent(itemView.getContext(),setsActivity.class);
                    setintent.putExtra("title",title);
                    itemView.getContext().startActivity(setintent);
                }
            });
        }
    }
}
