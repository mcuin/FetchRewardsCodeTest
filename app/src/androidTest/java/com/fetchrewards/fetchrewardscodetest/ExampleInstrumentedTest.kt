package com.fetchrewards.fetchrewardscodetest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fetchrewards.fetchrewardscodetest.utils.Item
import com.fetchrewards.fetchrewardscodetest.utils.ItemsRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    lateinit var itemsRepository: ItemsRepository
    lateinit var viewModel: FetchRewardsCodeTestViewModel
    @Before
    fun setUp() {

        itemsRepository = mockk<ItemsRepository>()

        every { itemsRepository.getItems() } returns flowOf(listOf(Item(0, 1, "Item 1"), Item(1, 2, "Item 2"), Item(2, 3, "Item 3")))

        viewModel = FetchRewardsCodeTestViewModel(itemsRepository)
    }

    @Test
    fun testSortAndGroupItems () {

        val items = listOf(Item(0, 1, "Item 1"), Item(1, 2, "Item 2"), Item(2, 3, "Item 3"))
        val map = viewModel.sortAndGroupItems(items)

        assert(map.size == 3)
        assertEquals(mapOf(1 to listOf(Item(0, 1, "Item 1")), 2 to listOf(Item(1, 2, "Item 2")), 3 to listOf(Item(2, 3, "Item 3"))), map)
    }
}