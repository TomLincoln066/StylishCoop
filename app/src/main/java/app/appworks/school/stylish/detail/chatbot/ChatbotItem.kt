package app.appworks.school.stylish.detail.chatbot

import app.appworks.school.stylish.data.ChatRobotDialog

sealed class ChatbotItem {
    class UserDialog(message: String): ChatbotItem() {
        override val id: String = "user"
    }

    class ChatbotDialog(chatRobotDialog: ChatRobotDialog): ChatbotItem() {
        override val id: String
            get() = "chatbot"
    }

    abstract val id: String
}