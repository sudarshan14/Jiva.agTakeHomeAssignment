package ag.jiva.mandi.data.repository

import ag.jiva.mandi.data.local.MandiDao
import ag.jiva.mandi.data.remote.MandiInfoApi
import ag.jiva.mandi.domain.model.SellerInfo
import ag.jiva.mandi.domain.model.SellingPriceInfo
import ag.jiva.mandi.domain.repository.MandiInfoRepository
import ag.jiva.mandi.util.Resource
import ag.jiva.mandi.util.printDebugLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException


class MandiInfoRepositoryImpl(
    private val api: MandiInfoApi,
    private val dao: MandiDao
) : MandiInfoRepository {

    override fun getSellerInfo(
        loyaltyCardId: String,
        sellerName: String
    ): Flow<Resource<List<SellerInfo>>> = flow {
//        emit(Resource.Loading())
//        val sellerInfo = dao.getSellerInfo(loyaltyCardId, sellerName).map { it.toSellerInfo() }
//        emit(Resource.Loading(data = sellerInfo))

        try {
            val remoteSellerInfo = api.getSellerInfo()
            dao.deleteSellerInfo(remoteSellerInfo.map { it.sellerName })
            dao.insertSellerInfo(remoteSellerInfo.map { it.toSellerInfo() })
        } catch (e: java.lang.Exception) {
            emit(
                Resource.Failure(
                    message = "Oops, something went wrong!",
                    data = emptyList()
                )
            )
        }

        val sellerInfoLatest =
            dao.getAllSellerInfo().map { it.toSellerInfo() }
        emit(Resource.Success(sellerInfoLatest))


    }

    override fun getAllSellerInfo(): Flow<Resource<List<SellerInfo>>> = flow{
        try {
            val remoteSellerInfo = api.getSellerInfo()
            dao.deleteSellerInfo(remoteSellerInfo.map { it.sellerName })
            dao.insertSellerInfo(remoteSellerInfo.map { it.toSellerInfo() })
        } catch (e: java.lang.Exception) {
            emit(
                Resource.Failure(
                    message = "Oops, something went wrong!",
                    data = emptyList()
                )
            )
        }

        val sellerInfoLatest =
            dao.getAllSellerInfo().map { it.toSellerInfo() }
        emit(Resource.Success(sellerInfoLatest))
    }
//    override fun getSellerLoyaltyCardId(sellerName: String): Flow<Resource<List<SellerInfo>>> =
//        flow {
//            val sellerId = dao.getSellerLoyaltyCardId(sellerName).map { it.toSellerInfo() }
//            emit(Resource.Success(sellerId))
//        }

    override fun getSellingPrice(): Flow<Resource<List<SellingPriceInfo>>> =
        flow {
            try {
                val salesPriceInfo = api.getVillageSellingPrice()
                printDebugLog("calling selling price ${salesPriceInfo.size}")
                dao.deleteSellingPrice()
                dao.insertSellingPrice(salesPriceInfo.map { it.toSellerInfo() })
            } catch (e: java.lang.Exception) {
                emit(
                    Resource.Failure(
                        message = "Oops, something went wrong!",
                        data = emptyList()
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Failure(
                        message = "Couldn't reach server, check your internet connection.",
                        data = emptyList()
                    )
                )
            }

            val salesPriceInfoLatest =
                dao.getSellingPrice().map { it.toSellingPriceInfo() }
            emit(Resource.Success(salesPriceInfoLatest))

        }
}