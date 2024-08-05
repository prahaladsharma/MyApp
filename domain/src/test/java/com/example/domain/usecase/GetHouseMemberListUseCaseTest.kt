package com.example.domain.usecase

import com.example.domain.model.MembersListEntity
import com.example.domain.repository.MemberRepository
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

class GetHouseMemberListUseCaseTest {

    private lateinit var SUT: GetHouseMemberListUseCase

    @MockK
    private lateinit var memberRepository: MemberRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = GetHouseMemberListUseCase(memberRepository)
    }

    //Success UseCase - When remotely get success data
    @Test
    fun invoke_with_success_response() = runTest {
        //Arrange
        val expectedResult = Result.Success(
            listOf(
                MembersListEntity(
                    name = "TEJ", slug = "slug"
                )
            )
        )
        coEvery { memberRepository.getHouseList() } returns expectedResult

        // Action
        val actualResult = SUT.invoke()

        //Assert
        expectThat(actualResult).isEqualTo(expectedResult)
    }

    //Failure UseCase - When general error
    @Test
    fun invoke_with_generalError_response() = runTest {
        //Arrange
        val expectedException = Result.Error<List<MembersListEntity>>("General Error")
        coEvery { memberRepository.getHouseList() } returns expectedException

        //Action
        val actualResult = SUT.invoke()

        // Assert
        expectThat(actualResult).isEqualTo(expectedException)

        coVerifySequence {
            memberRepository.getHouseList()
        }
    }

    // Failure UseCase - When network error
    @Test
    fun invoke_with_networkError_response() = runTest {
        // Arrange
        val networkException = Result.Error<List<MembersListEntity>>("Network Exception")
        coEvery { memberRepository.getHouseList() } returns networkException

        //Action
        val actualResult = SUT.invoke()

        // Assert
        assertThat(actualResult, `is`(networkException))
    }
}