package com.example.data.di

import com.example.data.repository.HouseDetailsRepositoryImpl
import com.example.data.repository.HouseListRepositoryImpl
import com.example.domain.repository.MemberDetailsRepository
import com.example.domain.repository.MemberRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindMemberRepository(houseListRepositoryImpl: HouseListRepositoryImpl): MemberRepository

    @Binds
    abstract fun bindMemberDetailRepository(houseDetailsRepositoryImpl: HouseDetailsRepositoryImpl): MemberDetailsRepository
}