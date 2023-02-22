package ag.jiva.mandi.di

import ag.jiva.mandi.data.local.MandiDatabase
import ag.jiva.mandi.data.remote.MandiInfoApi
import ag.jiva.mandi.data.repository.MandiInfoRepositoryImpl
import ag.jiva.mandi.domain.repository.MandiInfoRepository
import ag.jiva.mandi.domain.usecase.CalculateGrossPrice
import ag.jiva.mandi.domain.usecase.GetSellerInfo
import ag.jiva.mandi.domain.usecase.GetSellingPrice
import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MandiInfoModule {

    private const val DB_NAME = "AppDatabase"

    @Provides
    @Singleton
    fun provideSellerInfoUseCase(repository: MandiInfoRepository): GetSellerInfo {
        return GetSellerInfo(repository)
    }

    @Provides
    @Singleton
    fun provideSellingPriceUseCase(repository: MandiInfoRepository): GetSellingPrice {
        return GetSellingPrice(repository)
    }

    @Provides
    @Singleton
    fun provideGrossPrice(): CalculateGrossPrice {
        return CalculateGrossPrice()
    }

    @Provides
    @Singleton
    fun provideMandiInfoRepository(
        api: MandiInfoApi,
        db: MandiDatabase
    ): MandiInfoRepository {
        return MandiInfoRepositoryImpl(api, db.mandiDao)
    }

    @Provides
    @Singleton
    fun provideMandiDatabase(app: Application): MandiDatabase {
        return Room.databaseBuilder(app, MandiDatabase::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun provideMandiInfoApi(): MandiInfoApi {

        return MandiInfoApi

    }
}