package app.appworks.school.stylish.catalog.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProviders
import app.appworks.school.stylish.databinding.CatalogFilterPriceViewBinding
import com.facebook.CallbackManager

class CatalogSortFilterDialog : AppCompatDialogFragment(){



    //
    private val viewModel:CatalogSortFilterViewModel by lazy {
        ViewModelProviders.of(this).get(CatalogSortFilterViewModel::class.java)
    }


    //
//    lateinit var callbackManager:CallbackManager
    //
//    lateinit var binding: CatalogFilterPriceViewBinding


    //
    companion object {
        fun newInstance():CatalogSortFilterDialog =
            CatalogSortFilterDialog().apply {

            }

    }

    //
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {

        //
//        binding = CatalogFilterPriceViewBinding.inflate(inflater)
        //
//        binding.catalogFilterPrice = this
        //
//        binding.lifecycleOwner = this






//        return binding.root
//    }


}