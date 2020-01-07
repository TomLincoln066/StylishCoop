package app.appworks.school.stylish.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllProductResult(
    val error: String? = null,
    @Json(name = "data") val hotsList: List<Product>? = null,
    @Json(name = "next_paging") val nextPage: Int?
): Parcelable {
    fun toHomeItems(): List<HomeItem> {
        val items = mutableListOf<HomeItem>()

        hotsList?.let {products ->
            var i = 0
            products.forEach { product ->
                items.add(HomeItem.Title(product.title))

                when(i%2) {
                    0 -> items.add(HomeItem.FullProduct(product))
                    1 -> items.add(HomeItem.CollageProduct(product))
                }

                i+=1
            }
        }

        return items
    }
}