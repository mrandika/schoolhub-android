package space.mrandika.schoolhub.logic.Subject

import com.google.gson.annotations.SerializedName

class SubjectResponse (

    @SerializedName("id") val id : Int,
    @SerializedName("teacher") val teacher : String,
    @SerializedName("day") val day : Int,
    @SerializedName("class") val class_name : String,
    @SerializedName("subject") val subject : String,
    @SerializedName("time_in") val time_in : String,
    @SerializedName("time_out") val time_out : String
)