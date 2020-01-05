package app.appworks.school.stylish.catalog.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.User

class CatalogSortFilterViewModel : ViewModel(){

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user



}