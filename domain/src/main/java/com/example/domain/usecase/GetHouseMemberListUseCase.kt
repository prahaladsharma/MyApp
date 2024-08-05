package com.example.domain.usecase

import com.example.domain.utils.Result
import com.example.domain.model.MembersListEntity
import com.example.domain.repository.MemberRepository
import javax.inject.Inject

class GetHouseMemberListUseCase @Inject constructor(
    private val memberRepository: MemberRepository
) {
    suspend operator fun invoke(): Result<List<MembersListEntity>> {
        return memberRepository.getHouseList()
    }
}