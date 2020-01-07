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

    // declare an variable, have it inherits CatalogAdapter and set its initial value as null
    var catalogAdapter: CatalogAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        init()
        FragmentCatalogBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CatalogFragment
            viewpagerCatalog.let {
                tabsCatalog.setupWithViewPager(it)


                // use a if statement as a  decision checkpoint
                if (catalogAdapter == null) {

                    //what is an childFragmentManager???
                    catalogAdapter = CatalogAdapter(childFragmentManager)
                }

                //have ViewPager's adapter point to catalogAdapter(In computer memory)
                it.adapter = catalogAdapter

                //have ViewPager's addOnPageChangeListner injected a TabLayout (id:tabs_catalog) from fragment_catalog.xml)
                it.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabsCatalog))
            }


            // why do all this in OnCreateView?
            // what's OnCreateView? difference from OnCreate?
            //(1)setup filterSpinner's property, which is onItemSelectedListener, its entries with a OnItemSelectedListener.
            //(2)implement two functions 1.onNothingSelected 2.onItemSelected
            filterSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

                //onNothingSelected
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                //onItemSelected
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    // the position refers to the position in items of spinner's entries (check (id:) filter_spinner in fragment_catalog.xml)
                    //lambda expressions xx -> do something and return the result
                        when (position) {

                            0 -> setSort(Sort.POPULARITY, Order.ASCEND)

                            1 -> setSort( Sort.POPULARITY, Order.DESCEND)

                            //if position is 2 ( user selects third one(check spinner's entries in string.xml ), sort data with product's price ascending order)
                            2 -> setSort(Sort.PRICE, Order.ASCEND)

                            3 -> setSort(Sort.PRICE, Order.DESCEND)
                        }
                    }
                }



            return@onCreateView root
        }


    }



    // create a fuc setSort(declare two parameters, sort and order, inside the function)
    // Each parameter must have a name and type.

    fun setSort(sort: Sort, order: Order) {
        //why assigning sort parameter to catalogAdapter.sort??
        //what does catalogAdapter?.sort mean??

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