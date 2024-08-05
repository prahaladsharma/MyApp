package com.example.presentation.models

data class Member(
    val slug:String,
    val name:String,
    var members: List<Member> = arrayListOf()
)
