package com.example.project_roommate.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_roommate.databinding.ActivityFindUserBinding;

public class ActivityFindUser extends AppCompatActivity {
    private static final String TAG = ActivityFindUser.class+"로그";
    private ActivityFindUserBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mBinding = ActivityFindUserBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(mBinding.getRoot());

        initView();
        setEvent();
    }

    private void initView() {

    }

    private void setEvent() {

    }
}
