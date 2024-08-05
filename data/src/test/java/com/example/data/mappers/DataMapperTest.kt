package com.example.data.mappers

import com.example.data.models.MemberDetailsDTO
import com.example.data.models.MembersDTO
import com.example.domain.model.MemberDetailsEntity
import com.example.domain.model.MembersListEntity
import kotlinx.coroutines.test.runTest
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class DataMapperTest {

    private val dataMapper = DataMapper()

    @Test
    fun when_memberHouseList_is_null_should_return_empty_list() = runTest {
        // Arrange
        val expectedResult = listOf(
            MembersDTO(
                name = null,
                slug = null
            )
        )
        // Action
        val result = dataMapper.mapHouseMemberList(expectedResult)

        // Assert
        expectThat(result.isEmpty())
    }

    @Test
    fun when_getHouseMemberList_has_null_list_items() = runTest {
        // Arrange
        val actualResult = listOf(
            MembersDTO(
                name = null,
                slug = null
            )
        )

        val expectedResult = listOf(
            MembersListEntity(
                name = "",
                slug = ""
            )
        )

        // Action
        val result = dataMapper.mapHouseMemberList(actualResult)

        // Assert
        expectThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun when_getHouseMemberList_has_success_list_items_mapToModels() = runTest {
        // Arrange
        val actualResult = listOf(
            MembersDTO(
                name = "House Lannister of Casterly Rock",
                slug = "lannister"
            )
        )

        val expectedResult = listOf(
            MembersListEntity(
                name = "House Lannister of Casterly Rock",
                slug = "lannister"
            )
        )

        // Action
        val result = dataMapper.mapHouseMemberList(actualResult)

        // Assert
        expectThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun when_memberHouseListDetails_is_null_should_return_empty_list() = runTest {
        // Arrange
        val expectedResult = listOf(
            MemberDetailsDTO(
                name = null,
                slug = null
            )
        )
        // Action
        val result = dataMapper.mapHouseMemberDetails(expectedResult)

        // Assert
        expectThat(result.isEmpty())
    }

    @Test
    fun when_getHouseMemberListDetails_has_null_list_items() = runTest {
        // Arrange
        val actualResult = listOf(
            MemberDetailsDTO(
                name = null,
                slug = null
            )
        )

        val expectedResult = listOf(
            MemberDetailsEntity(
                name = "",
                slug = ""
            )
        )

        // Action
        val result = dataMapper.mapHouseMemberDetails(actualResult)

        // Assert
        expectThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun when_getHouseMemberDetails_has_success_list_items_mapToModels() = runTest {
        // Arrange
        val actualResult = listOf(
            MemberDetailsDTO(
                name = "House Lannister of Casterly Rock",
                slug = "lannister"
            )
        )

        val expectedResult = listOf(
            MemberDetailsEntity(
                name = "House Lannister of Casterly Rock",
                slug = "lannister"
            )
        )

        // Action
        val result = dataMapper.mapHouseMemberDetails(actualResult)

        // Assert
        expectThat(result).isEqualTo(expectedResult)
    }
}