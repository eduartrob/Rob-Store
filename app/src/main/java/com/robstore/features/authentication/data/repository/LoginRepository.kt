package com.robstore.features.authentication.data.repository


import com.robstore.features.authentication.data.model.UserValidateDTO
import com.robstore.features.core.network.RetrofitHelper
import com.robstore.features.authentication.data.model.LoginRequest

class LoginRepository(){
    private val loginService = RetrofitHelper.loginService

    suspend fun getUser(email: String, password: String): Result<UserValidateDTO> {
        val loginRequest = LoginRequest(email, password)
        return try {
            val response = loginService.validateEmail(loginRequest)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login fallido: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}