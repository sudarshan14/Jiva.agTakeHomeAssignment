package ag.jiva.mandi.domain.usecase

import org.junit.Assert.*

import org.junit.Test

class CalculateGrossPriceTest {

    @Test
    fun calculateGrossPrice() {

        val quantity = 20
        val loyaltyIndexPrice = 1.2f
        val salesPrice = 120f

        val grossPriceExpected = 2880.00f
        val calculatedGrossPrice = quantity*loyaltyIndexPrice*salesPrice

        assertEquals(grossPriceExpected,calculatedGrossPrice)

    }
}