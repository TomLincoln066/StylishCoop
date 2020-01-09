package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CouponBody(
    @Json(name = "data") val coupons: List<Coupon>?
): Parcelable