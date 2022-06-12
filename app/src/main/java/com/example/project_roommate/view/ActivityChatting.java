package com.example.project_roommate.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.project_roommate.contract.ContractChatting;
import com.example.project_roommate.contract.ContractFindUser;
import com.example.project_roommate.databinding.ActivityChattingBinding;
import com.example.project_roommate.model.ModelChatting;
import com.example.project_roommate.model.ModelSharedPreferences;
import com.example.project_roommate.presenter.PresenterChatting;
import com.example.project_roommate.presenter.adpater.AdapterChatting;

import org.jetbrains.annotations.Contract;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityChatting extends AppCompatActivity implements ContractChatting.View {
    private static final String TAG = ActivityChatting.class.getName() + " 로그";
    private ActivityChattingBinding mBinding;
    private ContractChatting.Presenter presenter;
    private AdapterChatting mAdapter;
    private ArrayList<ModelChatting> mList;
    private SimpleDateFormat mFormat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityChattingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent i = getIntent();
        String otherName = i.getStringExtra("otherName");

        initView(otherName);
        setEvent();
    }

    private void initView(String otherName) {
        presenter = new PresenterChatting(ActivityChatting.this);
        mList = new ArrayList<>();
        mAdapter = new AdapterChatting(mList, ActivityChatting.this);
        mFormat = new SimpleDateFormat("yyyyMMddhhmmss");

        mBinding.tvTitle.setText(otherName + "님과의 채팅");

        initRecyclerView();
    }

    private void setEvent() {
        presenter.getMessage();

        mBinding.imSend.setOnClickListener(v -> {
            String myName = ModelSharedPreferences.getUserName(ActivityChatting.this);
            String message = mBinding.edChatting.getText().toString();
            presenter.setChatting(ActivityChatting.this, message, getTime());
            mBinding.edChatting.setText("");
        });

        mBinding.tvExit.setOnClickListener(v -> {
            finish();
            presenter.deleteChatting(ActivityChatting.this);
        });

        mBinding.edChatting.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                switch (keyCode){
//                    case KeyEvent.KEYCODE_ENTER:
//                        String myName = ModelSharedPreferences.getUserName(ActivityChatting.this);
//                        String message = mBinding.edChatting.getText().toString();
//                        presenter.setChatting(ActivityChatting.this, message, getTime());
//
//                        mList.add(new ModelChatting(myName, message, getTime()));
//                        mAdapter.notifyItemInserted(mList.size());
//                        mBinding.reChatting.scrollToPosition(mList.size() - 1);
//                }

                return false;
            }
        });
    }

    @Override
    public void onSuccess(ArrayList<ModelChatting> cList) {

        mList = cList;
        initRecyclerView();
        mBinding.reChatting.scrollToPosition(mList.size() - 1);
    }

    public String getTime() {
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    public void initRecyclerView() {
        mAdapter = new AdapterChatting(mList, ActivityChatting.this);
        mBinding.reChatting.setLayoutManager(new LinearLayoutManager(this));
        mBinding.reChatting.setAdapter(mAdapter);
    }
}






















































































































