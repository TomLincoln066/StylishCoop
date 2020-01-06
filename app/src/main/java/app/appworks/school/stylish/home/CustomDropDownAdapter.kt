package app.appworks.school.stylish.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import app.appworks.school.stylish.data.CurrencyDropItem
import app.appworks.school.stylish.databinding.ItemSpinnerCurrencyBinding

class CustomDropDownAdapter(val context: Context, var currencyDropItem: CurrencyDropItem): BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            
        }
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class ItemRowHolder(val binding: ItemSpinnerCurrencyBinding) {
        fun bind(dropItem: CurrencyDropItem) {
            binding.dropItem = dropItem
            binding.executePendingBindings()
        }
    }

}