package app.appworks.school.stylish.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatbotBody(
    val question: Int,
    val productId: Long,
    val height: Double?,
    val weight: Double?
): Parcelable