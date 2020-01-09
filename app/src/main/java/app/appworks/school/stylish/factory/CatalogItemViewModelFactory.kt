package app.appworks.school.stylish.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.appworks.school.stylish.catalog.CatalogTypeFilter
import app.appworks.school.stylish.catalog.item.CatalogItemViewModel
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort

/**
 * Created by Wayne Chen on 2019-08-07.
 *
 * Factory for catalog item ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class CatalogItemViewModelFactory(
    private val stylishRepository: StylishRepository,
    private val catalogType: CatalogTypeFilter,
    private val sort: Sort,
    private val order: Order
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(CatalogItemViewModel::class.java) ->
                    // pass two more arguments to CatalogItemViewModel
                    CatalogItemViewModel(stylishRepository, catalogType, sort, order)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}