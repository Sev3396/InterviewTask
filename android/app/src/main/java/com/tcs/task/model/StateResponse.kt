package com.tcs.task.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
data class StateResponse(
    @SerializedName("domain")
    val domain: String,
    @SerializedName("state")
    val state: List<State>
)

@Keep
@Parcelize
data class State(
    @SerializedName("capital")
    val capital: String,
    @SerializedName("hotSpot")
    val hotSpot: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("name")
    val name: String
): Parcelable