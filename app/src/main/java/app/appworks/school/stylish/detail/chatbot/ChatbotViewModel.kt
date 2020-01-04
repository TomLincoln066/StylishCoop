package app.appworks.school.stylish.detail.chatbot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.ChatbotButton
import app.appworks.school.stylish.data.ChatbotDialog
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.UserDialog
import app.appworks.school.stylish.data.source.StylishRepository
import java.util.*

class ChatbotViewModel (private val stylishRepository: StylishRepository,
                        private val product: Product): ViewModel() {

    private val tag = "ChatbotViewModel"

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

        _chatbotDialogItem.value = listOf(
            ChatbotItem
            .ChatbotDialogItem(
                ChatbotDialog("您好，我是您的服務工具人。請問您需要什麼資訊呢？",
                    listOf(ChatbotButton("查詢商品合適大小", "searchSize"),
                        ChatbotButton("查詢 ...", "other stuff")
                    ), Date()
                )))
    }

    fun userSelectedAPI(api: String) {
        Log.i(tag, api)

        val list = _chatbotDialogItem.value?.toMutableList()
        list?.add(ChatbotItem.UserDialogItem(UserDialog(message = "Calling API for $api", sentDate = Date())))
        _chatbotDialogItem.value = list
    }

}