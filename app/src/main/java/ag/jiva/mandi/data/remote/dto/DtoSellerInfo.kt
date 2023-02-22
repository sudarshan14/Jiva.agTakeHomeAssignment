package ag.jiva.mandi.data.remote.dto

import ag.jiva.mandi.data.local.entity.EntitySellerInfo
import ag.jiva.mandi.domain.model.SellerInfo

data class DtoSellerInfo(
    val sellerName: String,
    val loyaltyCardId: String,
    val loyaltyIndex: Float,
    val villageName: String = ""
) {
    fun toSellerInfo(): EntitySellerInfo {
        return EntitySellerInfo(
            sellerName = sellerName,
            loyaltyCardId = loyaltyCardId,
            loyaltyIndex = loyaltyIndex
        )
    }
}
