package ag.jiva.mandi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class EntityProductInfo(
    val productId: Int,
    val productName: String,
    val productPrice:Float,
    val loyaltyCardId:String,
    val price: Int,
    @PrimaryKey val id: Int?
)