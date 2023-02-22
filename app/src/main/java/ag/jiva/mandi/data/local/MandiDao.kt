package ag.jiva.mandi.data.local

import ag.jiva.mandi.data.local.entity.EntityProductInfo
import ag.jiva.mandi.data.local.entity.EntitySellerInfo
import ag.jiva.mandi.data.local.entity.EntitySellingPriceInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MandiDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertProduct(product: List<EntityProductInfo>)

    @Query("DELETE FROM EntitySellerInfo WHERE sellerName IN(:sellerName)")
    suspend fun deleteSellerInfo(sellerName: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSellerInfo(product: List<EntitySellerInfo>)

    @Query("SELECT * FROM EntitySellerInfo where loyaltyCardId LIKE :loyaltyCardId || '%' or sellerName LIKE :sellerName || '%' ")
    suspend fun getSellerInfo(loyaltyCardId: String, sellerName: String): List<EntitySellerInfo>

    @Query("SELECT * FROM EntitySellerInfo")
    suspend fun getAllSellerInfo(): List<EntitySellerInfo>

//    @Query(value = "SELECT * from EntitySellerInfo where sellerName LIKE '%' ||:sellerName || '%' ")
//    suspend fun getSellerLoyaltyCardId(sellerName: String): List<EntitySellerInfo>

    @Query(value = "SELECT * from EntitySellingPriceInfo")
    suspend fun getSellingPrice(): List<EntitySellingPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSellingPrice(product: List<EntitySellingPriceInfo>)

    @Query("DELETE FROM EntitySellingPriceInfo")
    suspend fun deleteSellingPrice()

}