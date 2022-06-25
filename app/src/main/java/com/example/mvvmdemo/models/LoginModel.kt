package com.example.mvvmdemo.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel(val username: String?, val password: String?) : Parcelable