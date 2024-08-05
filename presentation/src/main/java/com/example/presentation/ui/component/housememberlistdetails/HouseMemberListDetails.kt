package com.example.presentation.ui.component.housememberlistdetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.presentation.models.HouseMemberState
import com.example.presentation.models.Member
import com.example.presentation.ui.intent.MemberDetailsIntent
import com.example.presentation.ui.widget.ErrorContent
import com.example.presentation.ui.widget.LoadingContent


@Composable
fun HouseMemberListDetails(
    navigation: NavHostController,
    viewModel: HouseMemberDetailViewModel = hiltViewModel(),
    slug:String
) {

    LaunchedEffect(viewModel) {
        viewModel.handleDetailsIntent(MemberDetailsIntent.LoadHouseMemberDetails, slug)
    }

    GetUiState(viewModel = viewModel, navigation = navigation)
}

@Composable
private fun GetUiState(
    viewModel: HouseMemberDetailViewModel,
    navigation: NavController
) {
    val state = viewModel.uiState.collectAsState()
    state.value?.let {
        when (it) {
            is HouseMemberState.Loading -> LoadingContent()

            is HouseMemberState.Success -> HouseListScreenDetails(member = it.member, navigation)

            is HouseMemberState.Error -> ErrorContent(it.message)
        }
    }
}


@Composable
fun HouseListScreenDetails(
    member: List<Member>,
    appNavController: NavController
) {
    member.map {
        MemberItemDetails(
            it,
            appNavController
        )
    }
}