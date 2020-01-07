package app.appworks.school.stylish.catalog.item

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import app.appworks.school.stylish.catalog.CatalogTypeFilter
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Factory for PagingDataSource
 */
class PagingDataSourceFactory(val type: CatalogTypeFilter, var sort: Sort? = null, var order: Order? = null) : DataSource.Factory<String, Product>() {

    val sourceLiveData = MutableLiveData<PagingDataSource>()

    override fun create(): DataSource<String, Product> {
        val source = PagingDataSource(type, sort, order)
        sourceLiveData.postValue(source)
        return source!!
    }

    fun refreshSortAndOrder(sort: Sort, order: Order) {
        this.sort = sort
        this.order = order

    }
}