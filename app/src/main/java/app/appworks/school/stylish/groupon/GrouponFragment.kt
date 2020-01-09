package app.appworks.school.stylish.groupon

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import app.appworks.school.stylish.databinding.FragmentGroupbuyBinding
import app.appworks.school.stylish.ext.getVmFactory
import app.appworks.school.stylish.ext.showToast
import app.appworks.school.stylish.ext.toPixel

enum class GroupBuyStatus(val string: String) {
    MISSINGYOUR("未確認"), NOTYET("未成團"), READY("已成團")
}

class GroupbuyFragment : Fragment() {

    private lateinit var binding: FragmentGroupbuyBinding
    private val viewModel by viewModels<GrouponFragmentViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupbuyBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@GroupbuyFragment
            spinnerGroupbuyStatus.adapter = GroupBuyStatusDropDownAdapter(GroupBuyStatus.values())
        }

        binding.recyclerGroupbuy.adapter = GrouponItemRecyclerAdapter(viewModel)


        viewModel.listOfInterest.observe(this, Observer {
            (binding.recyclerGroupbuy.adapter as? GrouponItemRecyclerAdapter)?.let {adapter ->
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
            }
        })

        binding.spinnerGroupbuyStatus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.switchTo(GroupBuyStatus.values()[position])
            }

        }

        binding.recyclerGroupbuy.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.top = 20.toPixel().toInt()
            }
        })

        viewModel.displayMessage.observe(this, Observer {
            activity?.showToast(it)
            viewModel.fetchProfile()
        })


        viewModel.fetchProfile()




        // Fetch User Profile


        // Fetch Group


//        binding.viewpagerGroupbuy.adapter =

        return binding.root
    }
}