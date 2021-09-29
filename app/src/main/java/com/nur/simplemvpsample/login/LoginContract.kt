package com.nur.simplemvpsample.login

interface LoginContract {

    interface View {
        fun showToast(msg : String)
        fun setEmailError()
        fun setPassword()
        fun openNext()
    }

    interface Presenter{
        fun login(email: String , password : String)
        fun isLoggedIn():Boolean
    }

}