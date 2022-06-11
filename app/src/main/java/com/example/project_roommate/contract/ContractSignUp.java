package com.example.project_roommate.contract;

import android.content.Context;

public interface ContractSignUp {
    interface View {
        public void success();
        public void failed();

    }

    interface Presenter{
        public void newSignUp(Context context, String name, String email, String password, String age, String location, String sTime, String hPay);
        public void logInCheck(String email,String password);
    }
}
