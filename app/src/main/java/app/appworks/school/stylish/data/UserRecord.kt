package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRecord(
    val id: Long,
    val title: String? = null,
    val price: Long = 0,
    @Json(name = "main_image") val image: String? = null
): Parcelable