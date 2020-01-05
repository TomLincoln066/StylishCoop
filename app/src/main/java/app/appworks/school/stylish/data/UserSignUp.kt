package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSignUp(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "access_expired") val accessExpired: Int,
    val user: User
) : Parcelable