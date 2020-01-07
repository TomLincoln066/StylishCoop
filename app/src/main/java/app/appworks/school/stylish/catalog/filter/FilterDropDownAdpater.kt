package app.appworks.school.stylish.catalog.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import app.appworks.school.stylish.databinding.ItemSpinnerFilterBinding

class FilterDropDownAdapter(private val items: Array<String>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ItemSpinnerFilterBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        binding.filter = items[position]
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