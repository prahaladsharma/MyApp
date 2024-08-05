package com.example.data.models

import com.google.gson.annotations.SerializedName

data class MembersDTO(
    @SerializedName("slug") var slug:String?,
    @SerializedName("name") var name:String?,
    @SerializedName("members" ) var members : ArrayList<Members>? = arrayListOf()
)

data class Members(
    @SerializedName("slug") var slug:String?,
    @SerializedName("name") var name:String?
)