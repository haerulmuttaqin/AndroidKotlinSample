package co.id.cpn.navsample.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.id.cpn.entity.InventoryItem

import co.id.cpn.entity.getFormattedPrice
import co.id.cpn.navsample.databinding.ItemInventoryBinding

class InventoryAdapter(private val onItemClicked: (InventoryItem) -> Unit) :
    ListAdapter<InventoryItem, InventoryAdapter.ListItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(ItemInventoryBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val currentPosition = getItem(position)
        holder.itemView.setOnClickListener { onItemClicked(currentPosition) }
        holder.bind(currentPosition)
    }

    class ListItemViewHolder(private var binding: ItemInventoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(inventory: InventoryItem) {
            binding.apply {
                itemName.text = inventory.itemName
                itemPrice.text = inventory.getFormattedPrice()
                itemQuantity.text = inventory.quantityInStock.toString()
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<InventoryItem>() {
            override fun areItemsTheSame(
                oldItem: InventoryItem,
                newItem: InventoryItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: InventoryItem,
                newItem: InventoryItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}