package com.example.project_roommate.contract;

import android.content.Context;

import com.example.project_roommate.model.ModelUserInfo;

import java.util.ArrayList;

public interface ContractFindUser {
    interface View {
        void success(ArrayList<ModelUserInfo> aList);
    }

    interface Presenter{
        void findUser(Context context);
    }
}
