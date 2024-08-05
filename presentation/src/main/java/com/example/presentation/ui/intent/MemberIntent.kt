package com.example.presentation.ui.intent

sealed class MemberIntent {
    data object LoadHouseMember: MemberIntent()

}