package com.example.project_roommate.presenter.adpater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_roommate.databinding.ActivitySearchItemBinding;
import com.example.project_roommate.model.ModelUserInfo;
import com.example.project_roommate.view.ActivityChatting;

import java.util.ArrayList;

public class AdapterFindUser extends RecyclerView.Adapter<FindUserViewHolder> {
    private ArrayList<ModelUserInfo> aList;
    private ActivitySearchItemBinding mBinding;
    private Context mContext;

    public AdapterFindUser(ArrayList<ModelUserInfo> list,Context context) {
        this.aList = list;
    }

    @NonNull
    @Override
    public FindUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mBinding = ActivitySearchItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new FindUserViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FindUserViewHolder holder, int position) {
        holder.setView(aList,position);
        holder.itemClick();
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }
}

class FindUserViewHolder extends RecyclerView.ViewHolder {
    private ActivitySearchItemBinding mBinding;

    public FindUserViewHolder(ActivitySearchItemBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public void setView(ArrayList<ModelUserInfo> aList,int position) {
        String name = aList.get(position).getName();
        String age = aList.get(position).getAge();
        String sleepTime = aList.get(position).getSleepTime();
        String hPay = aList.get(position).getHopePay();

        mBinding.tvUserName.setText(name);
        mBinding.tvUserInfo.setText("나이 : "+age +"세 \n취침시간 : "+sleepTime+"시 \n희망월세 : "+hPay+"만원");
    }

    public void itemClick() {
        mBinding.imChatting.setOnClickListener( v -> {
            Intent i = new Intent(itemView.getContext(), ActivityChatting.class);
            itemView.getContext().startActivity(i);
        });
    }
}


































