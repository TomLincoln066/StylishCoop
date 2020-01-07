package app.appworks.school.stylish.catalog.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.appworks.school.stylish.NavigationDirections
import app.appworks.school.stylish.catalog.CatalogTypeFilter
import app.appworks.school.stylish.databinding.FragmentCatalogItemBinding
import app.appworks.school.stylish.ext.getVmFactory
import app.appworks.school.stylish.network.LoadApiStatus
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort

/**
 * Created by Wayne Chen in Jul. 2019.
 */
class CatalogItemFragment(private val catalogType: CatalogTypeFilter, private var sort: Sort = Sort.POPULARITY, private var order:Order = Order.DESCEND) : Fragment() {

    /**
     * Lazily initialize our [CatalogItemViewModel].
     */
    private val viewModel by viewModels<CatalogItemViewModel> { getVmFactory(catalogType, sort, order) }

    private lateinit var adapter: PagingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentCatalogItemBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        adapter = PagingAdapter(PagingAdapter.OnClickListener {
            viewModel.navigateToDetail(it)
        })

        binding.recyclerCatalogItem.adapter = adapter

        viewModel.navigateToDetail.observe(this, Observer {
            it?.let {
                findNavController().navigate(NavigationDirections.navigateToDetailFragment(it))
                viewModel.onDetailNavigated()
            }
        })

        viewModel.pagingDataProducts.observe(this@CatalogItemFragment, Observer {
            (binding.recyclerCatalogItem.adapter as PagingAdapter).submitList(it)
        })

        binding.layoutSwipeRefreshCatalogItem.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.status.observe(this, Observer {
            it?.let {
                if (it != LoadApiStatus.LOADING)
                    binding.layoutSwipeRefreshCatalogItem.isRefreshing = false
            }
        })

        return binding.root
    }

    fun update(sort: Sort, order: Order) {
        this.sort = sort
        this.order = order
        viewModel.refreshWithSortAndOrder(sort, order)
    }
}