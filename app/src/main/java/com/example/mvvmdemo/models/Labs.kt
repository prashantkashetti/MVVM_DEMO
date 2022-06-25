package com.example.mvvmdemo.models

import com.google.gson.annotations.SerializedName

data class Labs(
    @SerializedName("missing_field") var missingField: String? = null,
)