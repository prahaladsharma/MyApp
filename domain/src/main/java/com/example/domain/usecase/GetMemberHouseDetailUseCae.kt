package com.example.domain.usecase

import com.example.domain.utils.Result
import com.example.domain.model.MemberDetailsEntity
import com.example.domain.repository.MemberDetailsRepository
import javax.inject.Inject

class GetMemberHouseDetailUseCae @Inject constructor(
    private val memberDetailsRepository: MemberDetailsRepository
) {

    suspend operator fun invoke(slug: String): Result<List<MemberDetailsEntity>> {
        return memberDetailsRepository.getHouseDetails(slug)
    }
}