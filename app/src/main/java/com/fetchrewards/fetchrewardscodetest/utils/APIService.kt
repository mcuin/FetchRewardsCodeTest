package com.fetchrewards.fetchrewardscodetest.utils

import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit service interface for fetching items from the API.
 */
interface APIService {

    /**
     * Retrieves a list of items from the API using a GET request.
     * returns: A [Response] object containing a [List] of [Item] objects.
     */
    @GET("hiring.json")
    suspend fun getItems(): Response<List<Item>>
}