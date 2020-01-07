package app.appworks.school.stylish.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.R
import app.appworks.school.stylish.data.source.remote.StylishRemoteDataSource
import app.appworks.school.stylish.databinding.FragmentCatalogBinding
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by Wayne Chen in Jul. 2019.
 */
class CatalogFragment : Fragment() {

    var catalogAdapter: CatalogAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        init()
        FragmentCatalogBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CatalogFragment
            viewpagerCatalog.let {
                tabsCatalog.setupWithViewPager(it)

                if (catalogAdapter == null) {
                    catalogAdapter = CatalogAdapter(childFragmentManager)
                }

                it.adapter = catalogAdapter
                it.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabsCatalog))
            }

            filterSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                        when (position) {

                            0 -> setSort(Sort.POPULARITY, Order.ASCEND)

                            1 -> setSort( Sort.POPULARITY, Order.DESCEND)

                            2 -> setSort(Sort.PRICE, Order.ASCEND)

                            3 -> setSort(Sort.PRICE, Order.DESCEND)
                        }
                    }
                }

//            filterButton.setOnClickListener{
//
//                findNavController().navigate(R.id.action_global_catalogSortFilterDialog)
//            }

            return@onCreateView root
        }


    }

    fun setSort(sort: Sort, order: Order) {
        catalogAdapter?.sort = sort
        catalogAdapter?.order = order
        catalogAdapter?.notifyDataSetChanged()
    }

//    private fun init() {
//        activity?.let {
//            ViewModelProviders.of(it).get(MainViewModel::class.java).apply {
//                currentFragmentType.value = CurrentFragmentType.CATALOG
//            }
//        }
//    }
}