package com.tcs.testapp.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StateCapitalDO(
    @SerializedName("domain")
    var domain: String?,
    @SerializedName("state")
    var state: List<State>?
):Parcelable
@Parcelize
data class State(
    @SerializedName("capital")
    var capital: String?,
    @SerializedName("hotSpot")
    var hotSpot: String?,
    @SerializedName("id")
    var id: Int,
    @SerializedName("imageUrl")
    var imageUrl: String?,
    @SerializedName("name")
    var name: String?
) : Parcelable