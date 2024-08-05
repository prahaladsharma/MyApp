package com.example.presentation.ui.intent

sealed class MemberDetailsIntent {
    data object LoadHouseMemberDetails: MemberDetailsIntent()
}