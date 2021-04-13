package com.tcs.task.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class StateResponse(
    @SerializedName("domain")
    val domain: String,
    @SerializedName("state")
    val state: List<State>
) {
    @Keep
    data class State(
        @SerializedName("capital")
        val capital: String,
        @SerializedName("hotSpot")
        val hotSpot: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("imageUrl")
        val imageUrl: String,
        @SerializedName("name")
        val name: String
    )
}