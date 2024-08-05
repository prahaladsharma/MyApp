package com.example.data.repository

import com.example.common.di.IoDispatcher
import com.example.data.api.HouseListApiService
import com.example.data.mappers.DataMapper
import com.example.domain.model.MemberDetailsEntity
import com.example.domain.repository.MemberDetailsRepository
import com.example.domain.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HouseDetailsRepositoryImpl @Inject constructor(
    private val houseListApiService: HouseListApiService,
    private val dataMapper: DataMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): MemberDetailsRepository {
    override suspend fun getHouseDetails(slug: String): Result<List<MemberDetailsEntity>> = withContext(ioDispatcher) {
        try {
            val response = houseListApiService.getHouseMemberDetails(slug)

            if (response.isSuccessful && response.body() != null){
                val memberListData = dataMapper.mapHouseMemberDetails(memberDetailsListResponse = response.body()!!)
                Result.Success(memberListData)
            } else{
                Result.Error("Unable to fetch house list  details results")
            }

        }catch (exc: Exception){
            Result.Error("Unable to fetch house list details results")
        }
    }


}