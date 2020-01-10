package app.appworks.school.stylish.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.AddGroupBuyBody
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.Result
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.ext.isValidEmail
import app.appworks.school.stylish.login.UserManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Add2GroupBuyViewModel (
    private val stylishRepository: StylishRepository,
    private val arguments: Product
) : ViewModel() {
    val emailA = MutableLiveData<String>()
    val emailB = MutableLiveData<String>()

    val errorMessageA = MutableLiveData<String>()
    val errorMessageB = MutableLiveData<String>()

    val emailEmpty = "請輸入 Email"
    val emailFormIncorrect = "請確定格式無誤"

    val success = MutableLiveData<Boolean>()

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)


    val displayMessage = MutableLiveData<String>()

    fun sendGroupRequest() {
        errorMessageA.value = null
        errorMessageB.value = null
        success.value = null

        val currentEmailA = emailA.value
        val currentEmailB = emailB.value

        if (currentEmailA == null) {
            errorMessageA.value = emailEmpty
            return
        }

        if (currentEmailB == null) {
            errorMessageB.value = emailEmpty
            return
        }


        if (!currentEmailA.isValidEmail()) {
            errorMessageA.value = emailFormIncorrect
            return
        }

        if (!currentEmailB.isValidEmail()) {
            errorMessageB.value = emailFormIncorrect
            return
        }

        if (currentEmailA == UserManager.userEmail || currentEmailB == UserManager.userEmail) {
            displayMessage.value = "請勿填" +
                    "自己的 email"
            return
        }

        UserManager.userToken?.let {token ->
            coroutineScope.launch {
                val result = stylishRepository.createGroupBuy(
                    AddGroupBuyBody(
                        friend1 = currentEmailA,
                        friend2 = currentEmailB,
                        productID = arguments.id,
                        token = token))

                when(result) {
                    is Result.Success -> {
                        if (result.data.error == null) {
                            displayMessage.value = "成功創團"
                            success.value = true
                        } else {
                            errorMessageB.value = result.data.error
                        }
                    }

                    is Result.Error -> {
                        Log.i("sendGroupRequest", "${result.exception}")
                    }

                    is Result.Fail -> {
                        errorMessageB.value = result.error
                    }
                }
            }
        }

    }
}