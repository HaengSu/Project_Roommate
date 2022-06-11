package com.example.project_roommate.presenter;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.project_roommate.contract.ContractSignUp;
import com.example.project_roommate.model.ModelSharedPreferences;
import com.example.project_roommate.view.ActivitySignUp;
import com.example.project_roommate.view.MainActivityLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
    private FirebaseFirestore db;
    private ModelSharedPreferences modelSharedPreferences;


    public PresenterSign(ContractSignUp.View view) {
        this.mView = view;
    }

    @Override
    public void newSignUp(Context context, String name, String email, String password, String location, String age, String sTime, String hPay) {
        activitySignUp = new ActivitySignUp();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        Map<String, String> userInfo = new HashMap<>();

        userInfo.put("name",name);
        userInfo.put("location",location);
        userInfo.put("age", age);
        userInfo.put("sleepTime", sTime);
        userInfo.put("hopePay", hPay);

        db.collection("userInfo").document(email).set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i(TAG, "onSuccess: 데이터 주입 성공");

                ModelSharedPreferences.setUserLocation(context,location);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i(TAG, "onFailure: 데이터 주입 실패");
            }
        });

        auth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(activitySignUp, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: 회원가입 성공");
                            mView.success();
                        } else {
                            mView.failed();
                            Log.d(TAG, "onComplete: 회원가입 실패");
                        }
                    }
                });
    }

    //파이어 베이스에 저장되어있는 이메일과 비밀번호를 체크
    @Override
    public void logInCheck(String email, String password) {
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
                                mView.success();
                                loginCheck = loginCheck + 1;
                            }
                        } else {
                            Log.d(TAG, "onComplete: 로그인 실패");
                            mView.failed();
                        }
                    }
                });
    }
}






































