package com.example.domain.usecase

import com.example.domain.model.MemberDetailsEntity
import com.example.domain.model.MembersListEntity
import com.example.domain.repository.MemberDetailsRepository
import com.example.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo


class GetMemberHouseDetailUseCaeTest {

    private val slug: String = "Lucifer"

    private lateinit var SUT: GetMemberHouseDetailUseCae

    @MockK
    private lateinit var memberDetailsRepository: MemberDetailsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = GetMemberHouseDetailUseCae(memberDetailsRepository)
    }

    //Success UseCase - If we pass parameters- When remotely get success data
    @Test
    fun invoke_with_parameter_success_response() = runTest {
        //Arrange
        val expectedResult = Result.Success(
            listOf(
                MemberDetailsEntity(
                    name = "TEJAS",
                    slug = "SLUG",
                    members = listOf(
                        MembersListEntity(
                            name = "Patrick",
                            slug = "Lucifier"
                        )
                    )
                )
            )
        )
        coEvery { memberDetailsRepository.getHouseDetails(slug) } returns expectedResult

        //Action
        val actualResult = SUT.invoke(slug)

        //Assert
        expectThat(actualResult).isEqualTo(expectedResult)
    }

    //Failure UseCase If we don't pass parameters- When remotely get success data
    @Test
    fun invoke_without_parameter_success_response() = runTest {
        //Arrange
        val expectedResult = Result.Success(
            listOf(
                MemberDetailsEntity(
                    name = "TEJAS",
                    slug = "SLUG",
                    members = listOf(
                        MembersListEntity(
                            name = "Patrick",
                            slug = "Lucifier"
                        )
                    )
                )
            )
        )
        coEvery { memberDetailsRepository.getHouseDetails("") } returns expectedResult

        //Action
        val actualResult = SUT.invoke("")

        //Assert
        expectThat(actualResult).isEqualTo(expectedResult)
    }

    //Failure UseCase - When general error
    @Test
    fun invoke_with_generalError_response() = runTest {
        //Arrange
        val expectedException = Result.Error<List<MemberDetailsEntity>>("General Error")
        coEvery { memberDetailsRepository.getHouseDetails(slug) } returns expectedException

        //Action
        val actualResult = SUT.invoke(slug)

        // Assert
        expectThat(actualResult).isEqualTo(expectedException)

        coVerifySequence {
            memberDetailsRepository.getHouseDetails(slug)
        }
    }

    // Failure UseCase - When network error
    @Test
    fun invoke_with_networkError_response() = runTest {
        // Arrange
        val networkException = Result.Error<List<MemberDetailsEntity>>("Network Exception")
        coEvery { memberDetailsRepository.getHouseDetails(slug) } returns networkException

        //Action
        val actualResult = SUT.invoke(slug)

        // Assert
        assertThat(actualResult, `is`(networkException))
    }

}