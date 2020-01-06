package app.appworks.school.stylish.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import app.appworks.school.stylish.R
import app.appworks.school.stylish.StylishApplication
import app.appworks.school.stylish.data.CurrencyDropItem
import app.appworks.school.stylish.databinding.ItemSpinnerCurrencyBinding


class CustomDropDownAdapter(private val items: Array<CurrencyDropItem>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemSpinnerCurrencyBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        binding.dropItem = items[position]
        return binding.root
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}

//class CustomDropDownAdapter(context: Context, private val currencyDropItems: List<CurrencyDropItem>):
//    ArrayAdapter<CurrencyDropItem>(context, R.layout.text, currencyDropItems) {
//
//    init {
//        Log.i("TAG", "${currencyDropItems}")
//        Log.i("TAG", "${currencyDropItems.count()}")
//    }
//
//    var inflater = LayoutInflater.from(context)
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return createView(position, convertView, parent)
//    }
//
//    override fun getItem(position: Int): CurrencyDropItem {
//        return currencyDropItems[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return 0
//    }
//
//    override fun getCount(): Int {
//        return currencyDropItems.count()
//    }
//
//    private fun createView(position: Int, recycledView: View?, parent: ViewGroup?): View {
//        val item = getItem(position)
//        val binding = ItemSpinnerCurrencyBinding
//            .inflate(LayoutInflater.from(parent!!.context), parent, false)
//        val view = recycledView ?: binding.root
//        binding.dropItem = item
//        binding.executePendingBindings()
//        return view
//    }
//
//}