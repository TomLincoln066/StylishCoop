package app.appworks.school.stylish.data

import androidx.annotation.StringRes
import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication

enum class Currency(@StringRes val abbRes: Int) {
    TWD(R.string.currency_twd),
    USD(R.string.currency_usd),
    JPY(R.string.currency_jpy)
}

data class CurrencyDropItem(
    val imageSrc: Int,
    val currency: Currency) {
    val string = StylishApplication.instance.getString(currency.abbRes)
    val image = StylishApplication.instance.getDrawable(imageSrc)
}