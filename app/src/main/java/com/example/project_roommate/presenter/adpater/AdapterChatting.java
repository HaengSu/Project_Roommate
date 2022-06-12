package com.example.project_roommate.presenter.adpater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_roommate.contract.ContractChatting;
import com.example.project_roommate.databinding.ActivityChattingItemBinding;
import com.example.project_roommate.model.ModelChatting;
import com.example.project_roommate.model.ModelSharedPreferences;

import java.util.ArrayList;

public class AdapterChatting extends RecyclerView.Adapter<ViewHolderChatting> {
    private static final String TAG = AdapterChatting.class.getName() +" 로그";
    private ArrayList<ModelChatting> cList;
    private ActivityChattingItemBinding binding;
    private Context mContext;

    public AdapterChatting(ArrayList<ModelChatting> list, Context context) {
        this.cList = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderChatting onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ActivityChattingItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolderChatting(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChatting holder, int position) {
        holder.setView(mContext,cList,position);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: cList.size = "+ cList.size());
        return cList.size();
    }
}

class ViewHolderChatting extends RecyclerView.ViewHolder {
    private ActivityChattingItemBinding binding;

    public ViewHolderChatting(ActivityChattingItemBinding b) {
        super(b.getRoot());
        this.binding = b;
    }

    public void setView(Context context,ArrayList<ModelChatting> cList,int position) {
        String myName = ModelSharedPreferences.getUserName(context);
        String time = cList.get(position).getTime();

        if (cList.get(position).getName().equals(myName)){  //내가 보낸 메시지일 경우
            binding.layoutOtherMessage.setVisibility(View.GONE);
            binding.layoutMyMessage.setVisibility(View.VISIBLE);

            binding.tvMyMessage.setText(cList.get(position).getMessage());
            binding.tvTime.setText(time.substring(8,10)+":"+time.substring(10,12));
        } else {    // 상대방이 보낸 메시지일 경우
            binding.layoutOtherMessage.setVisibility(View.VISIBLE);
            binding.layoutMyMessage.setVisibility(View.GONE);

            binding.tvOtherMessage.setText(cList.get(position).getMessage());
            binding.tvOtherTime.setText(time.substring(8,10)+":"+time.substring(10,12));
        }
    }

}







































































