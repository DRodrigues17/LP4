package br.com.fundatec.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    fun validateUserInput(email: String?, password: String?) {
        if (email.isNullOrEmpty() && password.isNullOrEmpty()) {
            state.value = ViewState.ShowError
        }
    }
}

sealed class ViewState {
    object ShowError : ViewState()
    object ShowErrorEmail : ViewState()
    object ShowErrorPassword : ViewState()
}