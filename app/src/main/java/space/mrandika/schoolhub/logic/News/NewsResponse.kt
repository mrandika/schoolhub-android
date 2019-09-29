package space.mrandika.schoolhub.logic.News

import com.google.gson.annotations.SerializedName

class NewsResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("categories") val categories : String,
    @SerializedName("user") val user : String,
    @SerializedName("has_meta") val has_meta : Int,
    @SerializedName("title") val title : String,
    @SerializedName("headline") val headline : String,
    @SerializedName("content") val content : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String
)