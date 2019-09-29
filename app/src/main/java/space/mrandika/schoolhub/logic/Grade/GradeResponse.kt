package space.mrandika.schoolhub.logic.Grade

import com.google.gson.annotations.SerializedName

class GradeResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("students") val students : String,
    @SerializedName("section") val section : String,
    @SerializedName("section_name") val section_name : String,
    @SerializedName("score") val score : Int
)