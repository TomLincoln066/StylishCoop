package app.appworks.school.stylish.groupon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.appworks.school.stylish.databinding.FragmentGroupbuyBinding

class GroupbuyFragment : Fragment() {

    private lateinit var binding: FragmentGroupbuyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupbuyBinding.inflate(inflater)


        return binding.root
    }
}