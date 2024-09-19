package com.fetchrewards.fetchrewardscodetest.utils

import com.google.gson.annotations.SerializedName

//Data class to deserialize the JSON response into a usable object
data class Item(@SerializedName("id") val id: Int, @SerializedName ("listId") val listId: Int, @SerializedName("name") val name: String?)