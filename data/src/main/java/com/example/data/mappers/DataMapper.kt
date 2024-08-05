package com.example.data.mappers

import com.example.data.models.HouseMembers
import com.example.data.models.MemberDetailsDTO
import com.example.data.models.MembersDTO
import com.example.domain.model.MemberDetailsEntity
import com.example.domain.model.MembersListEntity
import java.util.ArrayList
import javax.inject.Inject

class DataMapper @Inject constructor() {

    //Mapper function for House Member List
    fun mapHouseMemberList(memberListResponse: List<MembersDTO>): List<MembersListEntity> {

        return memberListResponse.map { result ->
            MembersListEntity(
                name = result.name.orEmpty(),
                slug = result.slug.orEmpty()
            )
        }
    }

    //Mapper function for House Member Details
    fun mapHouseMemberDetails(memberDetailsListResponse: List<MemberDetailsDTO>): List<MemberDetailsEntity> {
        return memberDetailsListResponse.map { result ->
            MemberDetailsEntity(
                name = result.name.orEmpty(),
                slug = result.slug.orEmpty(),
                members = getMembers(result.members)
            )
        }
    }
    private fun getMembers(members: ArrayList<HouseMembers>): List<MembersListEntity> {
        return members.map { result ->
            MembersListEntity(
                name = result.name!!,
                slug = result.slug!!
            )
        }
    }
}