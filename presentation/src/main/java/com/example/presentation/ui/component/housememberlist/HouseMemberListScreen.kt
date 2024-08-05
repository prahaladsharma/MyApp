package com.example.presentation.ui.component.housememberlist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.presentation.models.HouseMemberState
import com.example.presentation.models.Member
import com.example.presentation.ui.intent.MemberIntent
import com.example.presentation.ui.widget.ErrorContent
import com.example.presentation.ui.widget.LoadingContent
import com.example.presentation.ui.widget.TopBar

@Composable
fun HouseMemberListScreen(
    navigation: NavController,
    viewModel: HouseMemberListViewModel = hiltViewModel()
) {

    LaunchedEffect(viewModel) {
        viewModel.handleIntent(MemberIntent.LoadHouseMember)
    }

    LazyColumn {
        item {
            TopBar()
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            GetUiState(viewModel, navigation)
        }
    }

}

@Composable
private fun GetUiState(
    viewModel: HouseMemberListViewModel,
    navigation: NavController
) {
    val state = viewModel.uiState.collectAsState()
    state.value?.let {
        when (it) {
            is HouseMemberState.Loading -> LoadingContent()

            is HouseMemberState.Success -> HouseMemberContent(
                member = it.member,
                navigation
            )

            is HouseMemberState.Error -> ErrorContent(it.message)
        }
    }
}


@Composable
fun HouseMemberContent(member: List<Member>, navigation: NavController) {
    member.map {
        MemberItem(
            it,
            navigation
        )
    }
}




