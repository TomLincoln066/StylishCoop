package app.appworks.school.stylish.catalog.filter

import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProviders

class CatalogSortFilterDialog : AppCompatDialogFragment(){

    private val viewModel:CatalogSortFilterViewModel by lazy {
        ViewModelProviders.of(this).get(CatalogSortFilterViewModel::class.java)
    }






}