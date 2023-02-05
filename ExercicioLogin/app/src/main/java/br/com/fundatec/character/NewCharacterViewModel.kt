package br.com.fundatec.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewCharacterViewModel : ViewModel() {

    private val state = MutableLiveData<ViewCharacterState>()
    val viewState: LiveData<ViewCharacterState> = state

    fun validateUserInput(
        name: String?, url: String?, description: String?, age: String?,
        birthday: String?
    ) {
        if (name.isNullOrEmpty() || url.isNullOrEmpty() || description.isNullOrEmpty()
            || age.isNullOrEmpty() || birthday.isNullOrEmpty()
        ) {
            state.value = ViewCharacterState.ShowNullCampsError
        } else {
            state.value = ViewCharacterState.ShowSuccess
        }
    }
}

sealed class ViewCharacterState {
    object ShowNullCampsError : ViewCharacterState()
    object ShowSuccess : ViewCharacterState()
}