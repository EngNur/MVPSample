package com.nur.simplemvpsample.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.nur.simplemvpsample.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() , LoginContract.View{

    lateinit var presenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LoginPresenter(this)
        saveUser.setOnClickListener { presenter.login(email.editableText.toString(), password.editableText.toString()) }
    }
    override fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun setEmailError() {
        email.error = "Please enter valid non empty email"
    }

    override fun setPassword() {
        password.error = "Please enter valid non empty password, with 6 or more chars"
    }


    override fun openNext() {
        view?.let { Navigation.findNavController(it).navigate(LoginFragmentDirections.loginSuccess()) }
    }
}