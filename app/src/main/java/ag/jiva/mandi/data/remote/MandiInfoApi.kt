package ag.jiva.mandi.data.remote

import ag.jiva.mandi.data.remote.dto.DtoSellerInfo
import ag.jiva.mandi.data.remote.dto.DtoSellingPriceInfo

object MandiInfoApi {

    fun getSellerInfo(): List<DtoSellerInfo> {

        val sellerInfo1 = DtoSellerInfo("Ramesh Singh", "RS123",1.12f)
        val sellerInfo2 = DtoSellerInfo("Suresh Chand", "SC123",1.12f)
        val sellerInfo3 = DtoSellerInfo("Prakash Kohli", "PK123",1.12f)
        val sellerInfo4 = DtoSellerInfo("Vikas Sharma", "VS123",1.12f)
        val sellerInfo5 = DtoSellerInfo("Aditya Singh", "",0.98f)
        val sellerInfo6 = DtoSellerInfo("Harminder Singh", "",0.98f)

        val list = mutableListOf<DtoSellerInfo>()
        list.add(sellerInfo1)
        list.add(sellerInfo2)
        list.add(sellerInfo3)
        list.add(sellerInfo4)
        list.add(sellerInfo5)
        list.add(sellerInfo6)

        return list
    }


    fun getVillageSellingPrice(): List<DtoSellingPriceInfo> {

        val info1 = DtoSellingPriceInfo("Ramnagar", 120.01f)
        val info2 = DtoSellingPriceInfo("Bijnor", 110.11f)
        val info3 = DtoSellingPriceInfo("Shimla", 115f)
        val info4 = DtoSellingPriceInfo("Kashmir", 180.50f)

        val list = mutableListOf<DtoSellingPriceInfo>()
        list.add(info1)
        list.add(info2)
        list.add(info3)
        list.add(info4)

        return list

    }
}