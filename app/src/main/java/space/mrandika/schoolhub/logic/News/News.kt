package space.mrandika.schoolhub.logic.News

import com.google.gson.annotations.SerializedName

class News (
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : List<NewsResponse>
)