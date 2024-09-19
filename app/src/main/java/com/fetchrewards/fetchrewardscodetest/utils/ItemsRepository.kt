package com.fetchrewards.fetchrewardscodetest.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Repository class for fetching items from the API.
 */
class ItemsRepository @Inject constructor(private val apiService: APIService) {

    /**
     * Fetches items from the API and emits them as a flow. If an error occurs, an empty list is emitted for error handling
     */
    fun getItems(): Flow<List<Item>> = flow {
        emit(apiService.getItems().body() ?: emptyList())
    }.catch {
        emit(emptyList())
    }
}