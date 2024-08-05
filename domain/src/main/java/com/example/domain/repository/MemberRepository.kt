package com.example.domain.repository

import com.example.domain.utils.Result
import com.example.domain.model.MembersListEntity

interface MemberRepository {
    suspend fun getHouseList(): Result<List<MembersListEntity>>
}