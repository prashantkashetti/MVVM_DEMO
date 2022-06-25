package com.example.mvvmdemo.ui.home.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.db.AssociatedDrugEntity
import com.example.mvvmdemo.repo.AssociatedDrugsRepo
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AssociatedDrugUseCaseImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val associatedDrugsRepo: AssociatedDrugsRepo = mockk(relaxed = true)

    private lateinit var associatedDrugUseCase: AssociatedDrugUseCaseImpl
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        associatedDrugUseCase = AssociatedDrugUseCaseImpl(associatedDrugsRepo)
    }

    @Test
    fun `Given hour is 13, When getGreetMsg is called, Then greetMsg should emit Good Afternoon`() {
        val result = associatedDrugUseCase.getGreetMsg(13)

        assertEquals(result, "Good Afternoon")
    }

    @Test
    fun `Given hour is 18, When getGreetMsg is called, Then greetMsg should emit Good Evening`() {
        val result = associatedDrugUseCase.getGreetMsg(18)

        assertEquals(result, "Good Evening")
    }

    @Test
    fun `Given hour is 22, When getGreetMsg is called, Then greetMsg should emit Good Night`() {
        val result = associatedDrugUseCase.getGreetMsg(22)

        assertEquals(result, "Good Night")
    }

    @Test
    fun `Given hour is 9, When getGreetMsg is called, Then greetMsg should emit Good Morning`() {
        val result = associatedDrugUseCase.getGreetMsg(9)

        assertEquals(result, "Good Morning")
    }

    @Test
    fun `When getList method is called, Then should delete old list & save new list`() {
        runBlocking {
            associatedDrugUseCase.getList()
        }

        coVerifySequence {
            associatedDrugsRepo.getList()
            associatedDrugsRepo.deleteAssociatedDrugList()
            associatedDrugsRepo.saveAssociatedDrugList(any())
        }
    }

    @Test
    fun `When getAssociatedDrugList method is called, Then should return live data of list of AssociatedDrugEntity`() {
        val liveData = MutableLiveData<List<AssociatedDrugEntity>>()
        every { associatedDrugsRepo.getAssociatedDrugList() } returns liveData

        runBlocking {
            val result = associatedDrugUseCase.getAssociatedDrugList()
            assertEquals(liveData, result)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}