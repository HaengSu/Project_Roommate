package com.example.project_roommate.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.transition.Visibility;

import com.example.project_roommate.contract.ContractFindUser;
import com.example.project_roommate.model.ModelSharedPreferences;
import com.example.project_roommate.model.ModelUserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PresenterFindUser implements ContractFindUser.Presenter {
    private static final String TAG = PresenterFindUser.class.getName() + " 로그";
    private FirebaseFirestore db;
    private ContractFindUser.View mView;

    public PresenterFindUser(ContractFindUser.View view) {
        this.mView = view;
    }

    @Override
    public void findUser(Context context) {
        db = FirebaseFirestore.getInstance();

        String userLocation = ModelSharedPreferences.getUserLocation(context);
        db.collection("userInfo").whereEqualTo("location", userLocation).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<ModelUserInfo> aList = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        Log.i(TAG,  "document => "+document.getData().values());
                        String name = document.getData().get("name").toString();
                        String age = document.getData().get("age").toString();
                        String sTime = document.getData().get("sleepTime").toString();
                        String hopePay = document.getData().get("hopePay").toString();

                        ModelUserInfo mData = new ModelUserInfo(name, age, sTime, hopePay);
                        aList.add(mData);
                    }
                    Log.i(TAG, "onComplete: size = "+ aList.size());
                    mView.success(aList);

                } else {
                    Log.i(TAG, "onComplete: 데이터불러오기 실패");
                }
            }
        });
    }
}




































