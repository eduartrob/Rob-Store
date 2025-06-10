package com.robstore.features.authentication.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robstore.features.authentication.domain.LoginUseCase
import com.robstore.features.authentication.presentation.state.EmailValidationState
import com.robstore.features.authentication.presentation.state.PasswordValidatioinState
import com.robstore.features.core.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch




class LoginViewModel(private val sessionManager: SessionManager) : ViewModel(){
    private val loginUseCase = LoginUseCase()


    private val _emailInputText = MutableStateFlow("")
    val emailInputText: StateFlow<String> = _emailInputText

    private val _emailValidationState = MutableStateFlow<EmailValidationState?>(null)
    val emailValidationState: MutableStateFlow<EmailValidationState?> = _emailValidationState

    private var wasError = false
    private var hasEmailBeenFocused = false




    private val _password = MutableStateFlow("")
    val password : StateFlow<String> = _password

    private val _passwordValidationState = MutableStateFlow<PasswordValidatioinState?>(null)
    val passwordValidationState: MutableStateFlow<PasswordValidatioinState?> = _passwordValidationState

    private var wasErrorPassword = false

    private var _success = MutableLiveData<Boolean>(false)
    val success : LiveData<Boolean> = _success



    private var _error = MutableLiveData<String>("")
    val error : LiveData<String> = _error




    fun onEmailChange(email: String) {
        _emailInputText.value = email

        if (wasError) {
            _emailValidationState.value = EmailValidationState.Valid
            wasError = false
        }
    }
    fun onEmailFocusChanged(hasFocus: Boolean) {
        if (!hasFocus) {
            if (hasEmailBeenFocused) {
                validateEmail(_emailInputText.value)
            } else {
                hasEmailBeenFocused = true
            }
        }
    }
    private fun validateEmail(email: String) {
        _emailValidationState.value = when {
            email.isBlank() -> {
                wasError = true
                EmailValidationState.Empty
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                wasError = true
                EmailValidationState.Invalid
            }
            else -> EmailValidationState.Valid
        }
    }



    fun onPasswordChange (password : String) {
        _password.value = password

        if(wasErrorPassword){
            _passwordValidationState.value = PasswordValidatioinState.Valid
            wasErrorPassword = false
        }
    }


//    fun validateCredentials() {
//        val email = emailInputText.value
//        val password = password.value
//
//        when {
//            email.isBlank() || password.isBlank() -> {
//                _emailValidationState.value = EmailValidationState.Empty
//
//            }
//            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
//                _emailValidationState.value = EmailValidationState.Invalid
//            }
//            email != "eduart@gmail.com" -> {
//                _emailValidationState.value = EmailValidationState.NotRegistered
//            }
//            password != "prueba123" -> {
//                _emailValidationState.value = EmailValidationState.Error
//                _passwordValidationState.value = PasswordValidatioinState.Invalid
//            }
//            else -> {
//                _emailValidationState.value = EmailValidationState.Valid
//                _passwordValidationState.value = PasswordValidatioinState.Valid
//            }
//        }
//    }


    fun validateCredentials() {
        val email = emailInputText.value
        val password = password.value

        if (email.isBlank() || password.isBlank()) {
            _emailValidationState.value = EmailValidationState.Empty
            _passwordValidationState.value = PasswordValidatioinState.Invalid
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailValidationState.value = EmailValidationState.Invalid
            return
        }



        viewModelScope.launch {
            val result = loginUseCase(email, password)

            result.onSuccess { data ->
                if (data.token.isNotEmpty()) {
                    _emailValidationState.value = EmailValidationState.Valid
                    _passwordValidationState.value = PasswordValidatioinState.Valid
                    sessionManager.saveToken(data.token)
                    Log.d("LoginViewModel", "Login exitoso para $email")
                    _success.value = true
                } else {
                    _emailValidationState.value = EmailValidationState.Error
                    _passwordValidationState.value = PasswordValidatioinState.Invalid
                    Log.d("LoginViewModel", "Usuario no registrado o contraseña incorrecta")
                }
            }.onFailure { exception ->
                _emailValidationState.value = EmailValidationState.Error
                _passwordValidationState.value = PasswordValidatioinState.Invalid
                Log.e("LoginViewModel", "Error en login: ${exception.message}")
            }
        }
    }




}
