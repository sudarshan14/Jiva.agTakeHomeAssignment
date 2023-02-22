package ag.jiva.mandi.domain.usecase

import ag.jiva.mandi.domain.model.SellerInfo
import ag.jiva.mandi.domain.repository.MandiInfoRepository
import ag.jiva.mandi.util.Resource
import ag.jiva.mandi.util.printDebugLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetSellerInfo(
    private val repository: MandiInfoRepository
) {

    operator fun invoke(id: String, sellerName: String): Flow<Resource<List<SellerInfo>>> {
        if (id.isBlank() && sellerName.isBlank()) {
            return flow { }
        }
        return repository.getSellerInfo(id, sellerName)
    }

    fun getAllSellerInfo(): Flow<Resource<List<SellerInfo>>> {
        return repository.getAllSellerInfo()
    }
}