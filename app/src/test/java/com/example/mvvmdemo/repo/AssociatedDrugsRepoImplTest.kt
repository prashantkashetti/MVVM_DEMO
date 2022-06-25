package com.example.mvvmdemo.repo

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.db.AppDatabase
import com.example.mvvmdemo.db.AssociatedDrugDao
import com.example.mvvmdemo.db.AssociatedDrugEntity
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AssociatedDrugsRepoImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val context: Context = mockk()
    private val associatedDrugDao: AssociatedDrugDao = mockk(relaxed = true)
    private val appDatabase: AppDatabase = mockk(relaxed = true)

    private lateinit var associatedDrugsRepo: AssociatedDrugsRepo
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        every { appDatabase.associatedDrugDao() } returns associatedDrugDao
        associatedDrugsRepo = AssociatedDrugsRepoImpl(context, appDatabase)
    }

    @Test
    fun `When saveAssociatedDrugList is called, Then it should insert data into db`() {
        val list = listOf<AssociatedDrugEntity>()

        runBlocking {
            associatedDrugsRepo.saveAssociatedDrugList(list)
        }

        coVerify {
            associatedDrugDao.insertAll(list)
        }
    }

    @Test
    fun `When getAssociatedDrugList method is called, Then should return live data of list of AssociatedDrugEntity`() {
        val liveData = MutableLiveData<List<AssociatedDrugEntity>>()
        every { associatedDrugDao.getAllDrugs() } returns liveData

        runBlocking {
            val result = associatedDrugsRepo.getAssociatedDrugList()
            Assert.assertEquals(liveData, result)
        }
    }

    @Test
    fun `When deleteAssociatedDrugList is called, Then it should delete data from db`() {
        runBlocking {
            associatedDrugsRepo.deleteAssociatedDrugList()
        }

        coVerify {
            associatedDrugDao.deleteAll()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}