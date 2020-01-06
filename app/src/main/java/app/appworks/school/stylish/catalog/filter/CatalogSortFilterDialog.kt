package app.appworks.school.stylish.catalog.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.MainActivity
import app.appworks.school.stylish.R

import app.appworks.school.stylish.databinding.CatalogFilterViewTestBinding
import app.appworks.school.stylish.databinding.FragmentCatalogBinding
import com.facebook.CallbackManager

class CatalogSortFilterDialog : AppCompatDialogFragment() {


    //
    private val viewModel: CatalogSortFilterViewModel by lazy {
        ViewModelProviders.of(this).get(CatalogSortFilterViewModel::class.java)
    }


    //
//    lateinit var callbackManager:CallbackManager
    //
    lateinit var binding: CatalogFilterViewTestBinding


    //
//    companion object {
//        fun newInstance():FragmentCatalogBinding =
//            FragmentCatalogBinding().apply {
//
//            }
//
//    }

    //
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MessageDialog)
//    }

    //
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = CatalogFilterViewTestBinding.inflate(inflater)

        binding.catalogFilterViewTest = this

        binding.lifecycleOwner = this




        return binding.root

    }




}