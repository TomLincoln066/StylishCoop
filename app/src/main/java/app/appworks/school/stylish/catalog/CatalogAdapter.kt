package app.appworks.school.stylish.catalog

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import app.appworks.school.stylish.catalog.item.CatalogItemFragment
import app.appworks.school.stylish.network.Order
import app.appworks.school.stylish.network.Sort

/**
 * Created by Wayne Chen in Jul. 2019.
 */
class CatalogAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var sort = Sort.POPULARITY
    var order = Order.DESCEND

    override fun getItem(position: Int): Fragment {
        return CatalogItemFragment(CatalogTypeFilter.values()[position], sort, order)
    }
    override fun getCount() = CatalogTypeFilter.values().size

    override fun getPageTitle(position: Int): CharSequence? {
        return CatalogTypeFilter.values()[position].value
    }

    override fun getItemPosition(`object`: Any): Int {

        (`object` as? CatalogItemFragment)?.let {
            it.update(sort, order)
        }
        return super.getItemPosition(`object`)
    }
}