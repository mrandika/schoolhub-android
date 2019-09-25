package space.mrandika.schoolhub.logic.Today

import com.google.gson.annotations.SerializedName

data class TodayResponse(
    @SerializedName("id_user") val id_user : Int,
    @SerializedName("is_presenced") val is_presenced : Int,
    @SerializedName("balance") val balance : Int
)