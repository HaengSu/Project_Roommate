package com.example.project_roommate.contract;

public interface ContractSignUp {
    interface View {
        public void Success();
        public void Failed();

    }

    interface Presenter{
        public void NewSignUp(String email,String password,String nickName);
        public void LogInCheck(String email,String password);
    }
}
