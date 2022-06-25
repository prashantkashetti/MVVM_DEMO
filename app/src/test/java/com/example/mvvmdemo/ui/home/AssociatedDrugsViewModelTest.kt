package com.example.mvvmdemo.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mvvmdemo.db.AssociatedDrugEntity
import com.example.mvvmdemo.ui.home.usecase.AssociatedDrugUseCase
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class AssociatedDrugsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val associatedDrugUseCase: AssociatedDrugUseCase = mockk(relaxed = true)

    private val associatedDrugListObserver: Observer<List<AssociatedDrugEntity>> = mockk()
    private lateinit var viewModel: AssociatedDrugsViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = AssociatedDrugsViewModel(associatedDrugUseCase)
    }

    @Test
    fun `When getAssociatedDrugList method is called, Then associatedDrugList should emit list of AssociatedDrugEntity`() {
        val list = listOf<AssociatedDrugEntity>()
        every { associatedDrugUseCase.getAssociatedDrugList() } returns MutableLiveData(list)
        every { associatedDrugListObserver.onChanged(any()) } answers {}
        viewModel.associatedDrugList.observeForever(associatedDrugListObserver)

        val result = viewModel.associatedDrugList.value

        assertEquals(list, result)
        viewModel.associatedDrugList.removeObserver(associatedDrugListObserver)
    }

    @Test
    fun `Given hour is 13, When getGreetMsg is called, Then greetMsg should emit Good Afternoon`() {
        viewModel.getGreetMsg(13)

        verify {
            associatedDrugUseCase.getGreetMsg(13)
        }
    }

    @Test
    fun `Given hour is 18, When getGreetMsg is called, Then greetMsg should emit Good Evening`() {
        viewModel.getGreetMsg(18)

        verify {
            associatedDrugUseCase.getGreetMsg(18)
        }
    }

    @Test
    fun `Given hour is 22, When getGreetMsg is called, Then greetMsg should emit Good Night`() {
        viewModel.getGreetMsg(22)

        verify {
            associatedDrugUseCase.getGreetMsg(22)
        }
    }

    @Test
    fun `Given hour is 9, When getGreetMsg is called, Then greetMsg should emit Good Morning`() {
        viewModel.getGreetMsg(9)

        verify {
            associatedDrugUseCase.getGreetMsg(9)
        }
    }

    @Test
    fun `When fetchList method is called, Then old list should be deleted & new list should be saved`() {
        viewModel.fetchList()

        coVerify {
            associatedDrugUseCase.getList()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}