package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRefreshTokenResult(
    val error: String? = null,
    @Json(name = "data") val userSignIn: UserSignIn? = null): Parcelable