package ag.jiva.mandi.data.local

import ag.jiva.mandi.data.local.entity.EntityProductInfo
import ag.jiva.mandi.data.local.entity.EntitySellerInfo
import ag.jiva.mandi.data.local.entity.EntitySellingPriceInfo
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//@TypeConverters(
//    SignInScreenTypeConverter::class,
//)
@Database(
    entities = [EntityProductInfo::class, EntitySellerInfo::class, EntitySellingPriceInfo::class],
    version = 1
)
abstract class MandiDatabase : RoomDatabase() {

    abstract val mandiDao: MandiDao

}