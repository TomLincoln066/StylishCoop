package app.appworks.school.stylish.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import app.appworks.school.stylish.R
import app.appworks.school.stylish.databinding.DialogAdd2groupbuyBinding
import app.appworks.school.stylish.ext.getVmFactory
import app.appworks.school.stylish.ext.showToast

class Add2GroupBuyDialog : AppCompatDialogFragment() {

    private val viewModel by viewModels<Add2GroupBuyViewModel> { getVmFactory(DetailFragmentArgs.fromBundle(arguments!!).productKey) }


    private lateinit var binding: DialogAdd2groupbuyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.LoginDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogAdd2groupbuyBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonAdd2groupbuyCancel.setOnClickListener {
            dismiss()
        }

        binding.buttonAdd2groupbuyCreate.setOnClickListener {
            viewModel.sendGroupRequest()
        }

        viewModel.displayMessage.observe(this, Observer {
            activity?.showToast(it)
        })

        viewModel.success.observe(this, Observer {
            it?.let {
                dismiss()
            }
        })


        return binding.root
    }
}