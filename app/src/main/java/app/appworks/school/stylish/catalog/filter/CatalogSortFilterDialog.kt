package app.appworks.school.stylish.catalog.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.MainActivity
import app.appworks.school.stylish.R
import app.appworks.school.stylish.data.source.remote.StylishRemoteDataSource

import app.appworks.school.stylish.databinding.CatalogFilterViewTestBinding
import app.appworks.school.stylish.databinding.FragmentCatalogBinding
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort
import com.facebook.CallbackManager
import kotlinx.android.synthetic.main.fragment_catalog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class CatalogSortFilterDialog : AppCompatDialogFragment() {


    //
    private val viewModel: CatalogSortFilterViewModel by lazy {
        ViewModelProviders.of(this).get(CatalogSortFilterViewModel::class.java)
    }


    //
//    lateinit var callbackManager:CallbackManager
    //
    lateinit var binding: CatalogFilterViewTestBinding

    lateinit var binding1: FragmentCatalogBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.MessageDialog)

                binding1.fragmentCatalogFilterView?.filter_spinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                  suspend fun CoroutineScope(){
                 when(binding1.filterSpinner.id){
                    0-> StylishRemoteDataSource.getProductList("men", null, Sort.POPULARITY, Order.ASCEND)
                    1-> StylishRemoteDataSource.getProductList("men", null, Sort.POPULARITY, Order.DESCEND)
                    2-> StylishRemoteDataSource.getProductList("men", null, Sort.PRICE, Order.ASCEND)
                    3-> StylishRemoteDataSource.getProductList("men", null, Sort.PRICE, Order.DESCEND)
                 }
                  }

            }



            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

        }


    }

    //
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = CatalogFilterViewTestBinding.inflate(inflater)

        binding.catalogFilterViewTest = this

        binding.lifecycleOwner = this

        //close the Filter Dialog
        binding.buttonCancel.setOnClickListener{
            dismiss()
        }

        binding.buttonClearFilter.setOnClickListener {

            dismiss()
        }


        return binding.root

    }




}