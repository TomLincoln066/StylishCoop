package app.appworks.school.stylish.detail.chatbot

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.databinding.FragmentChatbotBinding
import app.appworks.school.stylish.detail.DetailViewModel

class ChatbotMainFragment : Fragment() {

    var product: Product? = null
    var viewModel: DetailViewModel? = null

    lateinit var binding: FragmentChatbotBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatbotBinding.inflate(inflater)
        viewModel?.chatbotStatus?.observe(this, Observer {
            when (it) {
                DetailViewModel.ChatbotStatus.SHOWN -> {
                    
                }
            }
        })




        return binding.root
    }


}