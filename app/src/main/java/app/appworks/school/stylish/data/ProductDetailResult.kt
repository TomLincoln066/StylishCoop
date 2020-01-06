package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDetailResult(
    val error: String? = null,
    @Json(name = "data") val product: Product? = null
):Parcelable