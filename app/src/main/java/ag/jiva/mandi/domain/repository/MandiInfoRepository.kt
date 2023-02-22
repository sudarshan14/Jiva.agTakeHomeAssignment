package ag.jiva.mandi.domain.repository

import ag.jiva.mandi.domain.model.SellerInfo
import ag.jiva.mandi.domain.model.SellingPriceInfo
import ag.jiva.mandi.util.Resource
import kotlinx.coroutines.flow.Flow


interface MandiInfoRepository {

    fun getSellerInfo(loyaltyCardId: String, sellerName:String): Flow<Resource<List<SellerInfo>>>
    fun getAllSellerInfo(): Flow<Resource<List<SellerInfo>>>
   // fun getSellerLoyaltyCardId(sellerName: String): Flow<Resource<List<SellerInfo>>>

    fun getSellingPrice(): Flow<Resource<List<SellingPriceInfo>>>
}