package app.appworks.school.stylish

import android.util.Log
import app.appworks.school.stylish.data.Result
import app.appworks.school.stylish.data.source.remote.StylishRemoteDataSource
import app.appworks.school.stylish.data.succeeded
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun signInWithEmail() {
//        CoroutineScope().launch {
//            val test = StylishRemoteDataSource.userSignIn("abc@gmail.com", "12345")
//            if (test.succeeded) {
//                (test as? Result.Success)?.let {
//                    Log.i("signInWithEmail", "result : ${it.data.userSignIn}")
//                    assertEquals((test as Result.Success).data.error == null, null)
//                }
//
//            } else {
//                (test as? Result.Fail)?.let {
//                    Log.i("signInWithEmail", "Error : ${it.error}")
//                }
//            }
//        }
    }
}
