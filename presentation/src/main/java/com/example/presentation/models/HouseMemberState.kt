package com.example.presentation.models

sealed class HouseMemberState {
    data object Loading: HouseMemberState()
    data class Success(val member: List<Member>): HouseMemberState()
    data class Error(val message:String): HouseMemberState()
}