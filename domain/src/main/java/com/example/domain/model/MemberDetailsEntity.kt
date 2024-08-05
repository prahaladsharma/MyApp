package com.example.domain.model

data class MemberDetailsEntity(
    val slug:String,
    val name:String,
    var members: List<MembersListEntity> = arrayListOf()
)