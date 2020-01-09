package app.appworks.school.stylish.detail.chatbot

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.*
import app.appworks.school.stylish.data.source.StylishRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

enum class ChatbotQuery(val api: String) {
    SIZE("querySize"), EVENTS("queryEvents")
}

class ChatbotViewModel (private val stylishRepository: StylishRepository,
                        private val product: Product): ViewModel() {

    private val tag = "ChatbotViewModel"

    private val chats = stylishRepository.getAllChats()

    val chatItems = Transformations.map(chats) {list ->
        list.map {chat ->
            if (chat.isBot) {
                ChatbotItem.ChatbotDialogItem(
                    ChatbotDialog(
                        message = chat.message,
                        chatButtons = chat.selections,
                        sentTime = chat.sentDate))
            } else {
                ChatbotItem.UserDialogItem(
                    UserDialog(
                        message = chat.message,
                        productId = chat.productId,
                        productTitle = chat.productTitle,
                        productImage = chat.productImage,
                        sentDate = chat.sentDate))
            }
        }
    }

    private val _chatbotDialogItem = MutableLiveData<List<ChatbotItem>>()

    val chatbotDialogItem: LiveData<List<ChatbotItem>>
        get() = _chatbotDialogItem

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    fun fetchDialogFor(message: String) {
        // fetch data
        if (message.trim().isEmpty()) {
            // Display the first
            displayGreedingMessage()
        }
    }

    fun displayGreedingMessage() {
        // Fetch Database
        val chat = Chat(message = "您好，我是您的服務工具人。請問您需要什麼資訊呢？",
            selections =
            listOf(
                ChatbotButton("查詢產品合適大小", ChatbotQuery.SIZE.api),
                ChatbotButton("查詢產品相關活動", ChatbotQuery.EVENTS.api)
            ), sentDate = Date(), isBot = true)
        CoroutineScope(Dispatchers.IO).launch {
            stylishRepository.insertChat(chat)
        }
    }

    fun userSelectedAPI(api: String, height: Double = 0.0, weight: Double = 0.0) {

        when(ChatbotQuery.valueOf(api)) {
            ChatbotQuery.SIZE -> {
                coroutineScope.launch {
                    val result = stylishRepository
                }
            }
            ChatbotQuery.EVENTS -> {

            }
        }

        val list = _chatbotDialogItem.value?.toMutableList()
        list?.add(ChatbotItem.UserDialogItem(UserDialog(message = "Calling API for $api", sentDate = Date())))
        _chatbotDialogItem.value = list
    }

}