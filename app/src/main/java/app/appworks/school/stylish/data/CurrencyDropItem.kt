package app.appworks.school.stylish.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication

enum class Currency(@StringRes val abbRes: Int, @DrawableRes val iconRes: Int) {
    TWD(R.string.currency_twd, R.drawable.twd),
    USD(R.string.currency_usd, R.drawable.usd),
    JPY(R.string.currency_jpy, R.drawable.jpy)
}

data class CurrencyDropItem(
    val currency: Currency) {
    val string = StylishApplication.instance.applicationContext.getString(currency.abbRes)
    val image = StylishApplication.instance.applicationContext.getDrawable(currency.iconRes)
}