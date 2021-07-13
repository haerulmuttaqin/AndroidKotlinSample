package co.id.cpn.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.NumberFormat

@Entity
data class InventoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val itemPrice: Double,
    val quantityInStock: Int
)

fun InventoryItem.getFormattedPrice(): String = NumberFormat.getCurrencyInstance().format(itemPrice)