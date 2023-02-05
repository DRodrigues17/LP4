package br.com.fundatec.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val regex: Regex = Regex(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
    )
    private val state = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = state

    fun validateUserInput(email: String?, password: String?) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            state.value = ViewState.ShowNullCampsError
        } else if (!email.matches(regex)) {
            state.value = ViewState.ShowInvalidEmailError
        } else {
            state.value = ViewState.ShowSuccess
        }
    }
}

sealed class ViewState {
    object ShowNullCampsError : ViewState()
    object ShowInvalidEmailError : ViewState()
    object ShowInvalidPasswordError : ViewState()
    object ShowSuccess : ViewState()
}