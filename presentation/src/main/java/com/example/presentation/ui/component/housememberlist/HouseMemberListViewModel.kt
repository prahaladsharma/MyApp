package com.example.presentation.ui.component.housememberlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.Result.Error
import com.example.domain.utils.Result.Success
import com.example.domain.model.MembersListEntity
import com.example.domain.usecase.GetHouseMemberListUseCase
import com.example.presentation.mappers.UiMapper
import com.example.presentation.models.HouseMemberState
import com.example.presentation.ui.intent.MemberIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseMemberListViewModel @Inject constructor(
    private val getHouseMemberListUseCase: GetHouseMemberListUseCase,
    private val uiMapper: UiMapper
) : ViewModel() {

    private var _uiState = MutableStateFlow<HouseMemberState?>(null)
    var uiState: MutableStateFlow<HouseMemberState?> = _uiState

    fun handleIntent(intent: MemberIntent) {
        viewModelScope.launch {
            when (intent) {
                is MemberIntent.LoadHouseMember -> getHouseMemberList()
            }
        }
    }

    suspend fun getHouseMemberList() {
        _uiState.value = HouseMemberState.Loading
        when (val result = getHouseMemberListUseCase()) {
            is Success<*> -> {
                _uiState.value = HouseMemberState.Success(uiMapper.mapMemberUi(memberListResponse = result.data as List<MembersListEntity>))
            }

            is Error<*> -> {
                _uiState.value = HouseMemberState.Error(result.error.toString())
            }
        }
    }
}