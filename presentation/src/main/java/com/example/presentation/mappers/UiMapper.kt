package com.example.presentation.mappers

import com.example.domain.model.MemberDetailsEntity
import com.example.domain.model.MembersListEntity
import com.example.presentation.models.Member
import javax.inject.Inject

class UiMapper @Inject constructor() {

    fun mapMemberUi(memberListResponse: List<MembersListEntity>): List<Member> {
        return memberListResponse.map { result ->
            Member(
                name = result.name,
                slug = result.slug
            )
        }
    }


    fun mapHouseMemberDetailsUi(memberDetailsListResponse: List<MemberDetailsEntity>): List<Member> {
        return  memberDetailsListResponse.map { result ->
            Member(
                name = result.name,
                slug = result.slug,
                members = getMembers(result.members)
            )
        }
    }
    private fun getMembers(members: List<MembersListEntity>): List<Member> {
        return members.map { result ->
            Member(
                name = result.name,
                slug = result.slug
            )
        }
    }
}