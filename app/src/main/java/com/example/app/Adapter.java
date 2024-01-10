package com.example.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final List<CardInfo> data;

    public Adapter(List<CardInfo> data) {

        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, priority, status, deadline;
        LinearLayout myLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            priority = itemView.findViewById(R.id.priority);
            status = itemView.findViewById(R.id.status);
            deadline = itemView.findViewById(R.id.deadline);
            myLayout = itemView.findViewById(R.id.mylayout);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CardInfo currentItem = data.get(position);

        holder.title.setText(currentItem.getTitle());
        holder.priority.setText("Priority - " + currentItem.getPriority());
        holder.status.setText(currentItem.getStatus());
        holder.deadline.setText("Deadline - " + currentItem.getDeadline());

        String status = currentItem.getStatus().toLowerCase();
        switch (status) {
            case "new":
                holder.status.setTextColor(Color.parseColor("#FF2222"));
                break;
            case "in progress":
                holder.status.setTextColor(Color.parseColor("#E6723C"));
                break;
            case "completed":
                holder.status.setTextColor(Color.parseColor("#43B548"));
                break;
        }

        holder.itemView.setOnClickListener(view -> {
            int adapterPosition = holder.getAdapterPosition();
            if (adapterPosition != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(holder.itemView.getContext(), edittask.class);
                intent.putExtra("id", currentItem.getId());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
