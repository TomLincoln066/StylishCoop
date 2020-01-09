package app.appworks.school.stylish.userlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import app.appworks.school.stylish.databinding.DialogUserLoginAlertBinding
import app.appworks.school.stylish.ext.getVmFactory


class userLoginDialog : AppCompatDialogFragment() {

    /**
     * Lazily initialize our [ProfileViewModel].
     */
    private val viewModel by viewModels<UserLoginDialogViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DialogUserLoginAlertBinding.inflate(inflater, container, false)

//        isCancelable = false

        //if buttonCloseJustWatchAd onclick, dismiss the userSignUp alert dialog
        binding.buttonCloseJustWatchAd.setOnClickListener{
            dismiss()
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        return binding.root

    }


}