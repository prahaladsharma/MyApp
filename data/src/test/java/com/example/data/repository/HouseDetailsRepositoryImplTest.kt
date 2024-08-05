package com.example.data.repository

import com.example.data.TestRunner
import com.example.data.api.HouseListApiService
import com.example.data.mappers.DataMapper
import com.example.data.models.HouseMembers
import com.example.data.models.MemberDetailsDTO
import com.example.data.models.MembersDTO
import com.example.domain.model.MemberDetailsEntity
import com.example.domain.model.MembersListEntity
import com.example.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.io.IOException


class HouseDetailsRepositoryImplTest {

    private lateinit var SUT: HouseDetailsRepositoryImpl

    @MockK
    private lateinit var houseListApiService: HouseListApiService

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = TestRunner()
    private val key:String = "slug"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = HouseDetailsRepositoryImpl(
            houseListApiService = houseListApiService,
            dataMapper = DataMapper(),
            ioDispatcher = mainCoroutineRule.testDispatcher)
    }

    @After
    fun tearDown(){
        unmockkAll()
    }

    // Success - Successfully get data from network
    @Test
    fun getHouseDetails_returns_valid_data_should_return_success() = runTest {
        // Arrange
        coEvery {
            houseListApiService.getHouseMemberDetails(key)
        } returns Response.success(
            listOf(
                MemberDetailsDTO(
                    name = "TEJ",
                    slug = "slug",
                    members = arrayListOf(
                        HouseMembers(
                            name = "TEJ",
                            slug = "slug",
                        )
                    )
                )
            )
        )

        val expectedResult = listOf(
            MemberDetailsEntity(
                name = "TEJ",
                slug = "slug",
                members = arrayListOf(
                    MembersListEntity(
                        name = "TEJ",
                        slug = "slug",
                    )
                )
            )
        )
        // Action
        val actualResult = SUT.getHouseDetails(key)

        // Assert
        assertTrue(actualResult is Result.Success)
        assertEquals(expectedResult, (actualResult as Result.Success).data)
    }

    // Success - List have null values
    @Test
    fun getHouseMemberDetails_has_empty_values_should_populate_with_default_and_return_success() = runTest {
        // Arrange
        coEvery {
            houseListApiService.getHouseMemberDetails(key)
        } returns Response.success(
            listOf(
                MemberDetailsDTO(
                    name = "",
                    slug = "",
                    members = arrayListOf(
                        HouseMembers(
                            name = "",
                            slug = "",
                        )
                    )
                )
            )
        )

        val expectedResult = listOf(
            MemberDetailsEntity(
                name = "",
                slug = "",
                members = arrayListOf(
                    MembersListEntity(
                        name = "",
                        slug = "",
                    )
                )
            )
        )
        // Action
        val actualResult = SUT.getHouseDetails(key)
        // Assert
        assertTrue(actualResult is Result.Success)
        assertEquals(expectedResult, (actualResult as Result.Success).data)
    }

    // Failure - Error - getHouseMemberList return null
    @Test
    fun getHouseMemberDetails_when_retrofit_unsuccessful_should_return_error() = runTest {
        // Arrange
        val errorMessage = "Unable to fetch house list  details results"
        coEvery {
            houseListApiService.getHouseMemberDetails(key)
        } returns Response.error(
            404,
            errorMessage.toResponseBody(null)
        )
        val expectedResult = Result.Error<MembersDTO>(
            error = "Unable to fetch house list  details results"
        )

        // Action
        val actualResult = SUT.getHouseDetails(key)

        // Assert
        assertTrue(actualResult is Result.Error)
        assertEquals(expectedResult.error, (actualResult as Result.Error).error)
    }

    // Failure - Exception error
    @Test
    fun getHouseMemberDetails_when_houseList_throws_error_should_catch_and_return_error() = runTest {
        // Arrange
        coEvery {
            houseListApiService.getHouseMemberDetails(key)
        } throws IOException()

        val expectedResult = Result.Error<List<MembersListEntity>>(
            error = "Unable to fetch house list details results"
        )

        // Act
        val actualResult = SUT.getHouseDetails(key)

        // Assert
        assertTrue(actualResult is Result.Error)
        assertEquals(expectedResult.error, (actualResult as Result.Error).error)
    }

}