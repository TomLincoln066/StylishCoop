package app.appworks.school.stylish.groupon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.data.GroupBuy
import app.appworks.school.stylish.data.Product
import app.appworks.school.stylish.data.Result
import app.appworks.school.stylish.data.source.StylishRepository
import app.appworks.school.stylish.login.UserManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GrouponFragmentViewModel(val stylishRepository: StylishRepository) : ViewModel() {


    // FETCH ALL GROUPBUY

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    private var finalWaitingList = MutableLiveData<List<GroupBuy>>()
    private var finalReadyList = MutableLiveData<List<GroupBuy>>()
    private val finalWaitingForYouList = MutableLiveData<List<GroupBuy>>()

//    val finalList = MutableLiveData<Map<String, List<GroupBuy>>>()
    private var userID : Int? = null

    var currentState: GroupBuyStatus = GroupBuyStatus.values().first()

    var listOfInterest = MutableLiveData<List<GroupBuy>>()

    fun switchTo(groupBuyStatus: GroupBuyStatus) {
        currentState = groupBuyStatus

        when(currentState) {
            GroupBuyStatus.READY -> listOfInterest.value = finalReadyList.value
            GroupBuyStatus.NOTYET -> listOfInterest.value = finalWaitingList.value
            GroupBuyStatus.MISSINGYOUR -> listOfInterest.value = finalWaitingForYouList.value
        }
    }

//    fun fetchProduct(forID: Long) {
//        var product: Product? = null
//        UserManager.userToken?.let {token ->
//            coroutineScope.launch {
//                val result = stylishRepository.getProductDetail(token, UserManager.userCurrency, forID.toString())
//                when(result) {
//                    is Result.Success -> {
//                        if (result.data.error == null) {
//                            product = result.data.product
//                        }
//                    }
//
//                    else -> {
//                        product = null
//                    }
//                }
//            }
//        }
//    }
//
//    fun fetchAllProducts() {
//
//    }



    fun fetchAllGroupBuy() {

        coroutineScope.launch {
            UserManager.userToken?.let {token ->
                val groupBuyResult = stylishRepository.getGroupBuys(token)

                when(groupBuyResult) {
                    is Result.Success -> {
                        groupBuyResult.data.data?.let {list ->
                            var waitingList = mutableListOf<GroupBuy>()
                            var readyList = mutableListOf<GroupBuy>()
                            val waitingForYouList = mutableListOf<GroupBuy>()

                            var newList = mutableListOf<GroupBuy>()
                            list.forEach {
                                val productResult = stylishRepository.getProductDetail(token, UserManager.userCurrency, it.productID.toString())
                                when(productResult) {
                                    is Result.Success -> {
                                        productResult.data.product?.let {product ->
                                            var newGroup = it.copy(product = product)
                                            newList.add(newGroup)
                                        }
                                    }
                                }
                            }


                           newList.forEach {groupBuy ->
                               var isWaitingForUser = false
                                groupBuy.users?.forEach { member ->
                                    if (member.userId == userID && member.confirm == 0) {
                                        isWaitingForUser = true
                                    }
                                }

                               if (isWaitingForUser) {
                                   waitingForYouList.add(groupBuy)
                               } else if (groupBuy.status == 1) {
                                   readyList.add(groupBuy)
                               } else {
                                   waitingList.add(groupBuy)
                               }
                            }

                            finalReadyList.value = readyList
                            finalWaitingList.value = waitingList
                            finalWaitingForYouList.value = waitingForYouList

                            switchTo(currentState)

//                            finalList.value = mapOf(
//                                GroupBuyStatus.READY.string to readyList.toList(),
//                                GroupBuyStatus.NOTYET.string to waitingList.toList(),
//                                GroupBuyStatus.MISSINGYOUR.string to waitingForYouList.toList()
//                            )



                        }
                    }

                    is Result.Fail -> {

                    }

                    is Result.Error -> {

                    }

                    else -> {

                    }
                }
            }
        }

    }

    fun fetchProfile() {
        coroutineScope.launch {
            UserManager.userToken?.let {token ->

                val profileResult = stylishRepository.getUserProfile(token)

                when(profileResult) {
                    is Result.Success -> {
                        profileResult.data.user?.id?.let {id ->
                            UserManager.userID = id
                            userID = id
                            fetchAllGroupBuy()
                        }
                    }

                    is Result.Error -> {
                        Log.i("fetchAllGroupBuy", "")
                    }

                    is Result.Fail -> {

                    }

                    else -> {

                    }
                }
            }
        }
    }

    val displayMessage = MutableLiveData<String>()

    fun joinGroup(groupBuy: GroupBuy) {
        coroutineScope.launch {
            UserManager.userToken?.let {token ->
                val result = stylishRepository.updateGroupBuy(token, groupBuy.productID)

                when(result) {
                    is Result.Success -> {
                        if (result.data.error == null) {
                            // Fetch all data and Display Toast
                            displayMessage.value = result.data.success
                        } else {
                            displayMessage.value = result.data.error
                        }
                    }

                    is Result.Fail -> {
                        displayMessage.value = result.error
                    }

                    is Result.Error -> {

                    }
                }

            }

        }
    }

}