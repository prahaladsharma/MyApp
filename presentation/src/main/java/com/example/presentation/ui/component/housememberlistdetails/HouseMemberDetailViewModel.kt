package com.example.presentation.ui.component.housememberlistdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.utils.Result.Error
import com.example.domain.utils.Result.Success
import com.example.domain.model.MemberDetailsEntity
import com.example.domain.usecase.GetMemberHouseDetailUseCae
import com.example.presentation.mappers.UiMapper
import com.example.presentation.models.HouseMemberState
import com.example.presentation.ui.intent.MemberDetailsIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HouseMemberDetailViewModel @Inject constructor(
    private val getMemberHouseDetailUseCae: GetMemberHouseDetailUseCae,
    private val uiMapper: UiMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<HouseMemberState?>(null)
    val uiState: MutableStateFlow<HouseMemberState?> = _uiState


    fun handleDetailsIntent(intent: MemberDetailsIntent, slug: String) {
        viewModelScope.launch {
            when (intent) {
                is MemberDetailsIntent.LoadHouseMemberDetails -> getHouseMemberDetails(slug)
            }
        }
    }

    private suspend fun getHouseMemberDetails(slug: String) {
        _uiState.value = HouseMemberState.Loading
        when (val result = getMemberHouseDetailUseCae(slug)) {
            is Success<*> -> {
                _uiState.value = HouseMemberState.Success(uiMapper.mapHouseMemberDetailsUi(memberDetailsListResponse = result.data as List<MemberDetailsEntity>))
            }

            is Error<*> -> {
                _uiState.value = HouseMemberState.Error(result.error.toString())
            }
        }

    }
}