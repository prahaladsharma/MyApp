package com.example.data.repository

import com.example.common.di.IoDispatcher
import com.example.data.api.HouseListApiService
import com.example.data.mappers.DataMapper
import com.example.domain.utils.Result
import com.example.domain.model.MembersListEntity
import com.example.domain.repository.MemberRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HouseListRepositoryImpl @Inject constructor(
    private val houseListApiService: HouseListApiService,
    private val dataMapper: DataMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MemberRepository {

    override suspend fun getHouseList(): Result<List<MembersListEntity>> =
        withContext(ioDispatcher) {

            //if(Network.isConnected())

            try {
                val response = houseListApiService.getHouseMemberList()
                if (response.isSuccessful && response.body() != null) {
                    val memberListData = dataMapper.mapHouseMemberList(memberListResponse = response.body()!!)
                    Result.Success(memberListData)
                } else {
                    Result.Error("Unable to fetch house list results")
                }

            } catch (exc: Exception) {
                Result.Error("Unable to fetch house list results")
            }
        }
}