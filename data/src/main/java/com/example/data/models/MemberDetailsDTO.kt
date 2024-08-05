package com.example.data.models

import com.google.gson.annotations.SerializedName

data class MemberDetailsDTO(
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("members") var members : ArrayList<HouseMembers> = arrayListOf()
)

data class HouseMembers(
    @SerializedName("slug") var slug:String?,
    @SerializedName("name") var name:String?
)