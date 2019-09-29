package space.mrandika.schoolhub.logic.Subject

import com.google.gson.annotations.SerializedName

class Subject (
    @SerializedName("code") val code : Int,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : List<SubjectResponse>
)