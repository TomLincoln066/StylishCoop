package app.appworks.school.stylish.groupon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.appworks.school.stylish.data.GroupBuy
import app.appworks.school.stylish.databinding.ItemGroupBuyBinding
import app.appworks.school.stylish.login.UserManager

class GrouponItemRecyclerAdapter(val viewModel: GrouponFragmentViewModel)
    :ListAdapter<GroupBuy, GrouponItemRecyclerAdapter.GroupBuyViewHolder>(Callback) {
    companion object Callback: DiffUtil.ItemCallback<GroupBuy>() {
        override fun areContentsTheSame(oldItem: GroupBuy, newItem: GroupBuy): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: GroupBuy, newItem: GroupBuy): Boolean {
            return oldItem.groupID == newItem.groupID
        }
    }

    class GroupBuyViewHolder(val binding: ItemGroupBuyBinding, val viewModel: GrouponFragmentViewModel): RecyclerView.ViewHolder(binding.root) {

        fun bind(groupBuy: GroupBuy) {
            binding.groupBuy = groupBuy

            var numberOfPersonWaiting = 0
            var isMissingUser = false

            groupBuy.users?.forEach {
                if (it.userId == UserManager.userID && it.confirm == 0) {
                    isMissingUser = true
                }

                if (it.confirm == 0) {
                    numberOfPersonWaiting += 1
                }

            }

            if (isMissingUser && numberOfPersonWaiting == 1) {
                binding.textGroupBuyRemainingText.text = "就差"
                binding.textGroupBuyRemaining.text = "你了"
            } else if (numberOfPersonWaiting > 0) {
                binding.textGroupBuyRemainingText.text = "還差"
                binding.textGroupBuyRemaining.text = "${numberOfPersonWaiting} 人"
            } else {
                binding.textGroupBuyRemainingText.text = ""
                binding.textGroupBuyRemaining.text = "成團"
            }

            if (isMissingUser) {
                binding.textGroupBuyRemaining.isClickable = true
                binding.textGroupBuyRemaining.setOnClickListener {
                    viewModel.joinGroup(groupBuy)
                }
            } else {
                binding.textGroupBuyRemaining.isClickable = false
            }


            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupBuyViewHolder {
        return GroupBuyViewHolder(ItemGroupBuyBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewModel)
    }

    override fun onBindViewHolder(holder: GroupBuyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


}