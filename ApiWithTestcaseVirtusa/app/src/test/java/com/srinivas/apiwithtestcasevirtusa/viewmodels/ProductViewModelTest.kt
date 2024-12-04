package com.srinivas.apiwithtestcasevirtusa.viewmodels

import app.cash.turbine.test
import com.srinivas.apiwithtestcasevirtusa.model.response.Products
import com.srinivas.apiwithtestcasevirtusa.model.response.ProductsResponse
import com.srinivas.apiwithtestcasevirtusa.repository.FakeProductRepository
import com.srinivas.apiwithtestcasevirtusa.utils.AppConstants
import com.srinivas.apiwithtestcasevirtusa.utils.MainDispatcherRule
import com.srinivas.apiwithtestcasevirtusa.utils.ResourceState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class ProductViewModelTest {

   /* @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()*/

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var fakeRepository: FakeProductRepository

    private lateinit var viewModel: ProductViewModel

    @Before
    fun setUp() {
//        val dataSource = mock(PostDataSource::class.java)
        fakeRepository = FakeProductRepository()
        viewModel = ProductViewModel(fakeRepository)
    }

    @Test
    fun `fetch Products then check for success response`() = runTest {
        val products = listOf(Products(1, "Test", "", "", 10.0, 1.0, 1.0, 10.0f, ""))
        val prodResponse = ProductsResponse(products, 1, 0, 1)

        viewModel.getProducts()

        viewModel.productResponse.test{
            assertEquals(ResourceState.Loading, awaitItem())
            advanceTimeBy(1000)
            assertEquals(ResourceState.Success(prodResponse), awaitItem())
            assertEquals(ResourceState.Error(AppConstants.COMMON_ERROR), awaitItem())
            ensureAllEventsConsumed()
        }

    }
}
