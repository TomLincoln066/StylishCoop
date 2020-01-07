package app.appworks.school.stylish.data.source

import androidx.lifecycle.LiveData
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.login.Currency
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Main entry point for accessing Stylish sources.
 */
interface StylishDataSource {

    suspend fun getProductAll(): Result<List<HomeItem>>

    suspend fun getMarketingHots(): Result<List<HomeItem>>

    suspend fun getProductList(type: String, paging: String? = null, sort: Sort? = null, order: Order? = null): Result<ProductListResult>

    suspend fun getUserProfile(token: String): Result<UserProfileResult>

    suspend fun userSignIn(fbToken: String): Result<UserSignInResult>

    suspend fun userSignIn(email: String, password: String): Result<UserSignInResult>

    suspend fun userSignUp(name: String, email: String, password: String): Result<UserSignUpResult>

    suspend fun userRefreshToken(token: String): Result<UserRefreshTokenResult>

    suspend fun checkoutOrder(token: String, orderDetail: OrderDetail): Result<CheckoutOrderResult>

    fun getProductsInCart(): LiveData<List<Product>>

    suspend fun getProductDetail(token: String, currency: Currency, productId: String): Result<ProductDetailResult>

    suspend fun getUserViewingRecord(token: String): Result<UserRecordsResult>

    /***
     * COUPON
     */
    suspend fun getAvaliableCoupons(token: String): Result<CouponMultitypeResult>

    suspend fun addNewCoupons(token: String, couponID: Int): Result<CouponMultitypeResult>

    suspend fun isProductInCart(id: Long, colorCode: String, size: String): Boolean

    suspend fun insertProductInCart(product: Product)

    suspend fun updateProductInCart(product: Product)

    suspend fun removeProductInCart(id: Long, colorCode: String, size: String)

    suspend fun clearProductInCart()

    suspend fun getUserInformation(key: String?): String
}
