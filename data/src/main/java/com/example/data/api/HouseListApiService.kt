package com.example.data.api

import com.example.data.models.MemberDetailsDTO
import com.example.data.models.MembersDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HouseListApiService {
    @GET("houses")
    suspend fun getHouseMemberList(): Response<List<MembersDTO>>

    @GET("house/{slug}")
    suspend fun getHouseMemberDetails(@Path("slug") slug: String): Response<List<MemberDetailsDTO>>
}