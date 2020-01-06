package app.appworks.school.stylish.data

import com.squareup.moshi.Json

data class CouponResult(
    @Json(name = "data") val coupons: List<Coupon>?,
    val error: String?
)