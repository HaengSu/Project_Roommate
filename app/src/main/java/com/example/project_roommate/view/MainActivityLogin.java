package com.example.project_roommate.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.project_roommate.contract.ContractSignUp;
import com.example.project_roommate.databinding.ActivityLoginBinding;
import com.example.project_roommate.presenter.PresenterSign;
import com.example.project_roommate.view.dialog.DialogFindPassword;

public class MainActivityLogin extends AppCompatActivity implements ContractSignUp.View {
    private ContractSignUp.Presenter presenter;
    private ActivityLoginBinding binding;
    private String email;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        initView(binding.getRoot());
        setEvent();
    }

    private void initView(View view) {
        presenter = new PresenterSign(this);
    }

    private void setEvent() {
        binding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = binding.edEmail.getText().toString();
                password = binding.edPassword.getText().toString();

                presenter.LogInCheck(email, password);
                if (!(email.isEmpty() && password.isEmpty())) {
                    presenter.LogInCheck(email, password);
                } else {
                    Toast.makeText(
                            MainActivityLogin.this, "모든 정보를 입력해주세요.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivityLogin.this,ActivitySignUp.class);
                startActivity(i);
            }
        });

        binding.tvFindUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDefaultDialog(v);
            }
        });
    }

    public void showDefaultDialog(View v){
        DialogFindPassword.getInstance(this).showDialog();
    }

    @Override
    public void Success() {
        Intent i = new Intent(this, ActivityFindUser.class);
        startActivity(i);
    }

    @Override
    public void Failed() {
        Toast.makeText(this, "입력한 정보가 틀렸습니다.", Toast.LENGTH_SHORT).show();
    }
}