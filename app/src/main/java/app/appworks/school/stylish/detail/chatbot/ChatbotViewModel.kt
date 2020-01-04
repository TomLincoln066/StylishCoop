package app.appworks.school.stylish.detail.chatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.source.StylishRepository

class ChatbotViewModel (private val stylishRepository: StylishRepository,
                        private val arguments: Product): ViewModel() {

    private val _chatbotDialogItem = MutableLiveData<List<ChatbotItem>>()

    val chatbotDialogItem: LiveData<List<ChatbotItem>>
        get() = _chatbotDialogItem

    fun fetchDialogFor(message: String) {
        // fetch data

        // append
        var list = chatbotDialogItem.value?.toMutableList()

        if (list == null) {
            // assign new value to list
        } else {
            // append the new answer to the old list
        }

        // update

    }

}