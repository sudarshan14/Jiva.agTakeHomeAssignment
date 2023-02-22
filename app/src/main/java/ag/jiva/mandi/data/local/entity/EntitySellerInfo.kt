package ag.jiva.mandi.data.local.entity

import ag.jiva.mandi.domain.model.SellerInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntitySellerInfo(
    val loyaltyCardId: String,
    val sellerName: String,
    val loyaltyIndex: Float,
    @PrimaryKey val id: Int? = null
) {
    fun toSellerInfo(): SellerInfo {
        return SellerInfo(
            loyaltyCardId = loyaltyCardId,
            sellerName = sellerName,
            loyaltyIndex = loyaltyIndex
        )
    }
}
