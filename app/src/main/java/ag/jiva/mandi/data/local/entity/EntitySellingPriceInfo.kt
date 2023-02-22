package ag.jiva.mandi.data.local.entity

import ag.jiva.mandi.domain.model.SellingPriceInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntitySellingPriceInfo(
    val villageName: String,
    val sellingPrice: Float,
    @PrimaryKey val id: Int? = null
) {
    fun toSellingPriceInfo(): SellingPriceInfo {
        return SellingPriceInfo(
            villageName = villageName,
            sellingPrice = sellingPrice
        )
    }
}
