package com.example.project_roommate.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_roommate.contract.ContractSignUp;
import com.example.project_roommate.databinding.ActivitySigupBinding;
import com.example.project_roommate.presenter.PresenterSign;

public class ActivitySignUp extends AppCompatActivity implements ContractSignUp.View {
    private static final String TAG = ActivitySignUp.class.toString();
    private ActivitySigupBinding binding;
    private ContractSignUp.Presenter presenter;
    private String email;
    private String password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        binding = ActivitySigupBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        initView(binding.getRoot());
        setEvent();
    }

    private void initView(View view) {
        presenter = new PresenterSign(this);
    }

    private void setEvent() {
        binding.tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = binding.edEmail.getText().toString();
                password = binding.edPassword.getText().toString();

                if (!(email.isEmpty() && password.isEmpty())) {
                    presenter.NewSignUp(email, password);
                } else {
                    Toast.makeText(ActivitySignUp.this,"모든 정보를 입력해주세요." , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void Success() {
        Toast.makeText(this, "환영합니다 오늘도 즐거운 하루 보내세요!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivityLogin.class);
        startActivity(i);
    }

    @Override
    public void Failed() {
        Toast.makeText(this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
    }
}













































































































