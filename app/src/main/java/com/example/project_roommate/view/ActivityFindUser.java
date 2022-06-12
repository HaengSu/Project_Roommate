package com.example.project_roommate.view;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.project_roommate.contract.ContractFindUser;
import com.example.project_roommate.databinding.ActivityFindUserBinding;
import com.example.project_roommate.model.ModelUserInfo;
import com.example.project_roommate.presenter.PresenterFindUser;
import com.example.project_roommate.presenter.adpater.AdapterFindUser;

import java.util.ArrayList;

public class ActivityFindUser extends AppCompatActivity implements ContractFindUser.View {
    private static final String TAG = ActivityFindUser.class.getName() +" 로그";
    private ActivityFindUserBinding mBinding;
    private ContractFindUser.Presenter presenter;
    private AdapterFindUser mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mBinding = ActivityFindUserBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(mBinding.getRoot());

        initView();
        setEvent();
    }

    private void initView() {
        presenter = new PresenterFindUser(ActivityFindUser.this);
    }

    private void setEvent() {
     mBinding.imFindUser.setOnClickListener(v -> {
         presenter.findUser(ActivityFindUser.this);
     });
    }

    @Override
    public void success(ArrayList<ModelUserInfo> aList) {
        mBinding.reFindUser.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AdapterFindUser(aList,ActivityFindUser.this);
        mBinding.reFindUser.setAdapter(mAdapter);
    }
}











































