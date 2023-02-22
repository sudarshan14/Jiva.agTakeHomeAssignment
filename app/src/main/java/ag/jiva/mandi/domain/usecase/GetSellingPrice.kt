package ag.jiva.mandi.domain.usecase

import ag.jiva.mandi.domain.model.SellingPriceInfo
import ag.jiva.mandi.domain.repository.MandiInfoRepository
import ag.jiva.mandi.util.Resource
import ag.jiva.mandi.util.printDebugLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow


class GetSellingPrice(
    private val repository: MandiInfoRepository
) {

    operator fun invoke(): Flow<Resource<List<SellingPriceInfo>>> {
        return repository.getSellingPrice()
    }

    fun getVillageSellingPrice(): Flow<Resource<List<SellingPriceInfo>>> {
        return repository.getSellingPrice()
    }
}