package com.example.project_roommate.contract;

import android.content.Context;

import com.example.project_roommate.model.ModelChatting;

import java.util.ArrayList;

public interface ContractChatting {
    interface View{
        void onSuccess(ArrayList<ModelChatting> cList);
    }

    interface Presenter{
        void getMessage();
        void setChatting(Context context, String message,String time);
    }
}
