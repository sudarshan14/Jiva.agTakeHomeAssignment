package ag.jiva.mandi.domain.usecase

import ag.jiva.mandi.domain.model.SellerInfo
import ag.jiva.mandi.domain.repository.MandiInfoRepository
import ag.jiva.mandi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetSellerLoyaltyCardId(
    private val repository: MandiInfoRepository
) {

//    operator fun invoke(name: String): Flow<Resource<List<SellerInfo>>> {
//        if (name.isBlank()) {
//            return flow { }
//        }
//        return repository.getSellerLoyaltyCardId(name)
//    }
}