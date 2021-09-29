package com.nur.simplemvpsample.login

import java.util.regex.Pattern

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    private var loggedIn = false

    override fun login(email: String, password: String) {
        if (validateEmail(email) && validatePassword(password)) {
            view.showToast("Done")
            loggedIn = true
            view.openNext()
        } else {
            view.showToast("Something wrong!")
        }
    }

    fun setIsLoggedIn(isLogged : Boolean){
        loggedIn = isLogged
    }
    override fun isLoggedIn(): Boolean {
        return loggedIn
    }

    fun validateEmail(email: String): Boolean {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        if (email.isEmpty() || !emailPattern.matcher(email).matches()) {
            view.setEmailError()
            return false
        }
        return true
    }

    fun validatePassword(password: String): Boolean {
        if (password.isEmpty() || password.length < 6) {
            view.setPassword()
            return false
        }
        return true
    }


}