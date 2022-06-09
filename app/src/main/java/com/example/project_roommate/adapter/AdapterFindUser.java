package com.example.project_roommate.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterFindUser extends RecyclerView.Adapter<FindUserViewHolder> {
    @NonNull
    @Override
    public FindUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FindUserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class FindUserViewHolder extends RecyclerView.ViewHolder {

    public FindUserViewHolder(@NonNull View itemView) {
        super(itemView);
    }
}
