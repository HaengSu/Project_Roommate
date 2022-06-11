package com.example.project_roommate.presenter;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_roommate.contract.ContractChatting;
import com.example.project_roommate.model.ModelChatting;
import com.example.project_roommate.model.ModelSharedPreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class PresenterChatting implements ContractChatting.Presenter {
    private static final String TAG = PresenterChatting.class.getName() + " 로그";
    private ContractChatting.View mView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private ArrayList<ModelChatting> cList = new ArrayList<>();

    public PresenterChatting(ContractChatting.View view) {
        this.mView = view;
    }

    @Override
    public void getMessage() {
        database.getReference().child("chatting").orderByChild("time").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.i(TAG, "onChildAdded: 변화감지");
                ModelChatting d = snapshot.getValue(ModelChatting.class);
                cList.add(d);
                mView.onSuccess(cList);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void setChatting(Context context, String message, String time) {
        String name = ModelSharedPreferences.getUserName(context);
        DatabaseReference databaseReference = database.getReference("chatting");

        ModelChatting chatting = new ModelChatting(name, message, time);
        databaseReference.push().setValue(chatting).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i(TAG, "onSuccess: 메세지 전송 완료");
            }
        });
    }
}








































































