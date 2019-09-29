package space.mrandika.schoolhub.logic.Today

import com.google.gson.annotations.SerializedName

data class Today (
    @SerializedName("data") val data : List<TodayResponse>
)