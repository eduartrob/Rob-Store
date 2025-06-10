package com.robstore.features.authentication.domain

import com.robstore.features.authentication.data.model.UserValidateDTO
import com.robstore.features.authentication.data.repository.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(email: String, password: String): Result<UserValidateDTO> {
        return repository.getUser(email, password)
    }
}
