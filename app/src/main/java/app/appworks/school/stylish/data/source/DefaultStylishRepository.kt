package app.appworks.school.stylish.data.source

import androidx.lifecycle.LiveData
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.login.Currency
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Concrete implementation to load Stylish sources.
 */
class DefaultStylishRepository(private val stylishRemoteDataSource: StylishDataSource,
                               private val stylishLocalDataSource: StylishDataSource,
                               private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : StylishRepository {

    override suspend fun getUserViewingRecord(token: String): Result<UserRecordsResult> {
        return stylishRemoteDataSource.getUserViewingRecord(token)
    }

    override suspend fun getProductDetail( token: String, productId: String, currency: Currency):
            Result<ProductDetailResult> {
        return stylishRemoteDataSource.getProductDetail(token, productId, currency)
    }

    override suspend fun userSignUp(name: String, email: String, password: String
    ): Result<UserSignUpResult> {
        return stylishRemoteDataSource.userSignUp(name, email, password)
    }

    override suspend fun userSignIn(email: String, password: String): Result<UserSignInResult> {
        return stylishRemoteDataSource.userSignIn(email, password)
    }

    override suspend fun userRefreshToken(token: String): Result<UserSignInResult> {
        return stylishRemoteDataSource.userSignIn(token)
    }

    override suspend fun getMarketingHots(): Result<List<HomeItem>> {
        return stylishRemoteDataSource.getMarketingHots()
    }

    override suspend fun getProductList(type: String, paging: String?, sort: Sort?, order: Order?
    ): Result<ProductListResult> {
        return stylishRemoteDataSource.getProductList(type, paging, sort, order)
}

    override suspend fun getUserProfile(token: String): Result<User> {
        return stylishRemoteDataSource.getUserProfile(token)
    }

    override suspend fun userSignIn(fbToken: String): Result<UserSignInResult> {
        return stylishRemoteDataSource.userSignIn(fbToken)
    }

    override suspend fun checkoutOrder(
        token: String, orderDetail: OrderDetail): Result<CheckoutOrderResult> {
        return stylishRemoteDataSource.checkoutOrder(token, orderDetail)
    }

    override fun getProductsInCart(): LiveData<List<Product>> {
        return stylishLocalDataSource.getProductsInCart()
    }

    override suspend fun isProductInCart(id: Long, colorCode: String, size: String): Boolean {
        return stylishLocalDataSource.isProductInCart(id, colorCode, size)
    }

    override suspend fun insertProductInCart(product: Product) {
        stylishLocalDataSource.insertProductInCart(product)
    }

    override suspend fun updateProductInCart(product: Product) {
        stylishLocalDataSource.updateProductInCart(product)
    }

    override suspend fun removeProductInCart(id: Long, colorCode: String, size: String) {
        stylishLocalDataSource.removeProductInCart(id, colorCode, size)
    }

    override suspend fun clearProductInCart() {
        stylishLocalDataSource.clearProductInCart()
    }

    override suspend fun getUserInformation(key: String?): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
