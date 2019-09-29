package space.mrandika.schoolhub.logic.Grade

import com.google.gson.annotations.SerializedName

class Grade (
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : List<GradeResponse>
)