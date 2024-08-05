package com.example.domain.repository

import com.example.domain.utils.Result
import com.example.domain.model.MemberDetailsEntity

interface MemberDetailsRepository {
    suspend fun getHouseDetails(slug: String): Result<List<MemberDetailsEntity>>
}