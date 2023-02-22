package ag.jiva.mandi.domain.usecase

import ag.jiva.mandi.util.roundToTwoDecimalPoints
import kotlinx.coroutines.flow.flow


class CalculateGrossPrice {


    fun calculateGrossPrice(quantity: Float, loyaltyIndex: Float, sellingPrice: Float) = flow {
        emit((quantity * loyaltyIndex * sellingPrice).roundToTwoDecimalPoints())
    }
}