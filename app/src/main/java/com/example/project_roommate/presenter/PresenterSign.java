package com.example.project_roommate.presenter;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.project_roommate.contract.ContractSignUp;
import com.example.project_roommate.view.ActivitySignUp;
import com.example.project_roommate.view.MainActivityLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * 회원가입 정보를 가지고 파이어 베이스와 통신하는 presenter
 */

public class PresenterSign implements ContractSignUp.Presenter {
    private static final String TAG = "로그";
    private ContractSignUp.View mView;
    private FirebaseAuth auth;
    private ActivitySignUp activitySignUp;
    private MainActivityLogin activityLogin;
    private int loginCheck = 0;


    public PresenterSign(ContractSignUp.View view) {
        this.mView = view;
    }

    @Override
    public void NewSignUp(String email, String password) {
        activitySignUp = new ActivitySignUp();
        auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(activitySignUp, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: 회원가입 성공");
                            mView.Success();
                        } else {
                            mView.Failed();
                            Log.d(TAG, "onComplete: 회원가입 실패");
                        }
                    }
                });
    }

    //파이어 베이스에 저장되어있는 이메일과 비밀번호를 체크
    @Override
    public void LogInCheck(String email, String password) {
        activityLogin = new MainActivityLogin();
        auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(activityLogin, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //로그인이 두번되는 firebase 오류를 방지하기 위해서 설정
                            if (loginCheck == 0) {
                                Log.d(TAG, "onComplete: 로그인 성공");
                                mView.Success();
                                loginCheck = loginCheck+1;
                            }
                        } else {
                            Log.d(TAG, "onComplete: 로그인 실패");
                            mView.Failed();
                        }
                    }
                });
    }
}






































