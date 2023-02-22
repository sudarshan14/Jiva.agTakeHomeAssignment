package ag.jiva.mandi.util

import java.math.RoundingMode
import java.text.DecimalFormat


fun Float.roundToTwoDecimalPoints(): Float {

    val df = DecimalFormat("#.##")
    return try {
        df.roundingMode = RoundingMode.UP
        df.format(this).toFloat()
    } catch (e: Exception) {
        0.00f
    }


}