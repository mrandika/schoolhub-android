package space.mrandika.schoolhub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Authorization (
    @SerializedName("token") val token : String
): Parcelable