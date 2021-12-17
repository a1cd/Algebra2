@Suppress("unused")
object Constants {
    fun convert(number: Number): String {
        val initial = "1234567890.-"
        val convert = "¹²³⁴⁵⁶⁷⁸⁹⁰ᐧ⁻"
        var strNum: String
        if ((number.toDouble() % 1.0) == 0.0) {
            strNum = number.toInt().toString()
        } else strNum = number.toDouble().toString()
        for ((index, _) in initial.withIndex()) strNum = strNum.replace(initial[index], convert[index])
        return strNum
    }
    object Multiplication {
        const val X = "×"
        const val DOT = "⋅"
        val d = Constants.convert(37.40)
    }
    object Division {
        const val SIGN = "÷"
        const val SLASH = "∕"
        const val LONG_DIV = "⟌"
    }
    object Super {
        const val EXP1 = "¹"
        const val SQUARED = "²"
        const val CUBED = "³"
        const val EXP4 = "⁴"
        const val EXP5 = "⁵"
        const val EXP6 = "⁶"
        const val EXP7 = "⁷"
        const val EXP8 = "⁸"
        const val EXP9 = "⁹"
        const val EXP0 = "⁰"
        const val POINT = "ᐧ"
    }
}