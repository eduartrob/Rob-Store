package com.robstore.features.authentication.presentation.state

sealed class EmailValidationState {
    object Empty : EmailValidationState()
    object Invalid : EmailValidationState()
    object NotRegistered :  EmailValidationState()
    object Valid : EmailValidationState()
    object Error : EmailValidationState()
}

sealed class PasswordValidatioinState {
    object Invalid: PasswordValidatioinState()
    object Valid: PasswordValidatioinState()
}