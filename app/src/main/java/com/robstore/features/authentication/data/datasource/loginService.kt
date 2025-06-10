package com.robstore.features.authentication.data.datasource

import com.robstore.features.authentication.data.model.LoginRequest
import com.robstore.features.authentication.data.model.UserValidateDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService{
    /*@GET("users/{username}")
    suspend fun validateUsername(@Path("username") username : String) : Response<UsernameValidateDTO>
    */


    @POST("api/users/sign-in")
    suspend fun validateEmail(@Body request: LoginRequest): Response<UserValidateDTO>





    //@POST("users")
    //suspend fun createUser(@Body request : CreateUserRequest) : Response<UserDTO>
}