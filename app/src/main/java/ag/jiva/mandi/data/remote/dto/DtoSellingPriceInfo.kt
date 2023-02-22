package ag.jiva.mandi.data.remote.dto

import ag.jiva.mandi.data.local.entity.EntitySellerInfo
import ag.jiva.mandi.data.local.entity.EntitySellingPriceInfo
import ag.jiva.mandi.domain.model.SellerInfo

data class DtoSellingPriceInfo(
    val villageName: String,
    val sellingPrice: Float,
) {
    fun toSellerInfo(): EntitySellingPriceInfo {
        return EntitySellingPriceInfo(
            villageName = villageName,
            sellingPrice = sellingPrice
        )
    }
}
