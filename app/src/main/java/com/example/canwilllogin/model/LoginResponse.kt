package com.example.canwilllogin.model

class LoginResponse {
    var responseCode: String? = null
    var responseMessage: String? = null

    override fun toString(): String {
        return "LoginResponse{" +
                "responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                '}'
    }
}