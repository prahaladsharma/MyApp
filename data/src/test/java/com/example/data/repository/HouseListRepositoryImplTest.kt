package com.example.data.repository

import com.example.data.TestRunner
import com.example.data.api.HouseListApiService
import com.example.data.mappers.DataMapper
import com.example.data.models.MembersDTO
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

class HouseListRepositoryImplTest {

    private lateinit var SUT: HouseListRepositoryImpl

    @MockK
    private lateinit var houseListApiService: HouseListApiService

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = TestRunner()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        SUT = HouseListRepositoryImpl(
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
    fun getHouseMemberList_returns_valid_data_should_return_success() = runTest {
        // Arrange
        coEvery {
            houseListApiService.getHouseMemberList()
        } returns Response.success(
            listOf(
                MembersDTO(
                    name = "TEJ",
                    slug = "slug"
                )
            )
        )

        val expectedResult = listOf(
            MembersListEntity(
                name = "TEJ",
                slug = "slug")
        )
        // Action
        val actualResult = SUT.getHouseList()

        // Assert
        assertTrue(actualResult is Result.Success)
        assertEquals(expectedResult, (actualResult as Result.Success).data)
    }

    // Success - List have null values
    @Test
    fun getHouseMemberList_has_empty_values_should_populate_with_default_and_return_success() = runTest {
        // Arrange
        coEvery {
            houseListApiService.getHouseMemberList()
        } returns Response.success(
            listOf(
                MembersDTO(
                    name = "",
                    slug = ""
                )
            )
        )

        val expectedResult = listOf(
            MembersListEntity(
                name = "",
                slug = ""
            )
        )
        // Action
        val actualResult = SUT.getHouseList()
        // Assert
        assertTrue(actualResult is Result.Success)
        assertEquals(expectedResult, (actualResult as Result.Success).data)
    }

    // Failure - Error - getHouseMemberList return null
    @Test
    fun getHouseMemberList_when_retrofit_unsuccessful_should_return_error() = runTest {
        // Arrange
        val errorMessage = "Unable to fetch house list results"
        coEvery {
            houseListApiService.getHouseMemberList()
        } returns Response.error(
            404,
            errorMessage.toResponseBody(null)
        )
        val expectedResult = Result.Error<MembersDTO>(
            error = "Unable to fetch house list results"
        )

        // Action
        val actualResult = SUT.getHouseList()

        // Assert
        assertTrue(actualResult is Result.Error)
        assertEquals(expectedResult.error, (actualResult as Result.Error).error)
    }

    // Failure - Exception error
    @Test
    fun getHouseMemberList_when_houseList_throws_error_should_catch_and_return_error() = runTest {
        // Arrange
        coEvery {
            houseListApiService.getHouseMemberList()
        } throws IOException()

        val expectedResult = Result.Error<List<MembersListEntity>>(
            error = "Unable to fetch house list results"
        )

        // Act
        val actualResult = SUT.getHouseList()

        // Assert
        assertTrue(actualResult is Result.Error)
        assertEquals(expectedResult.error, (actualResult as Result.Error).error)
    }
}