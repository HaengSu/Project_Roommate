package com.example.project_roommate.view.dialog;


import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.project_roommate.R;
import com.example.project_roommate.view.MainActivityLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class DialogFindPassword extends Dialog implements View.OnClickListener {
    private static final String TAG = DialogFindPassword.class.toString() + "로그";
    private ImageView cancel;
    private Button send;
    private EditText ed_email;
    private FirebaseAuth mAuth;
    private static DialogFindPassword customDialog;
    private MainActivityLogin activityLogin;
    private String emailContents;

    public DialogFindPassword(@NonNull Context context) {
        super(context);
    }

    public static DialogFindPassword getInstance(Context context) {
        customDialog = new DialogFindPassword(context);
        return customDialog;
    }

    public void showDialog() {
        customDialog.setContentView(R.layout.dialog_find_user_password);
        customDialog.setCanceledOnTouchOutside(false);
        customDialog.setCancelable(true);
        customDialog.show();
        customDialog.findViewById(R.id.cancel).setOnClickListener(this);
        customDialog.findViewById(R.id.send).setOnClickListener(this);
        cancel = customDialog.findViewById(R.id.cancel);
        send = customDialog.findViewById(R.id.send);
        ed_email = customDialog.findViewById(R.id.ed_find_email);
    }

    @Override
    public void onClick(View v) {
        if (cancel.equals(v)) {
            customDialog.dismiss();
        } else if (send.equals(v)) {
            activityLogin = new MainActivityLogin();
            emailContents = ed_email.getText().toString();
            Log.d(TAG, "onClick: 이메일 = " + emailContents);

            mAuth = FirebaseAuth.getInstance();
            mAuth.sendPasswordResetEmail(emailContents)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(v.getContext(), "메일을 보내드렸습니다.", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onClick: SendEmail is success.");
                                customDialog.dismiss();
                            } else {
                                Toast.makeText(v.getContext(), "가입되어 있지 않은 이메일 입니다.", Toast.LENGTH_SHORT)
                                        .show();
                                Log.d(TAG, "onClick: SendEmail is fail.");
                            }
                        }
                    });
        }
    }
}























































