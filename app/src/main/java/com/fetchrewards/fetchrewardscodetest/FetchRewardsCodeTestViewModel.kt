package com.fetchrewards.fetchrewardscodetest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetchrewards.fetchrewardscodetest.utils.Item
import com.fetchrewards.fetchrewardscodetest.utils.ItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FetchRewardsCodeTestViewModel @Inject constructor(itemsRepository: ItemsRepository) : ViewModel() {

    // Loading state is exposed as a StateFlow to show or hide the loading indicator
    private val _loadingFlow = MutableStateFlow(true)
    val loading = _loadingFlow.asStateFlow()

    // Items are sorted and exposed as a StateFlow to be observed by the UI when they change
    val items = itemsRepository.getItems().map { items ->
        _loadingFlow.emit(false)
        sortAndGroupItems(items)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyMap())

    /**
     * Sorts and groups the items by listId and then sorts the items by name.
     * @param items The list of items to sort and group.
     * @return A map of the sorted and grouped items.
     */
    fun sortAndGroupItems(items: List<Item>): Map<Int, List<Item>> {
        return items.filter { item -> !item.name.isNullOrBlank() }
            .sortedWith(compareBy<Item> { item -> item.listId }.thenBy { item -> item.name }).groupBy { item -> item.listId }
    }
}