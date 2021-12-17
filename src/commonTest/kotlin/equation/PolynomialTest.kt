package equation

import kotlin.test.Test
import kotlin.test.assertEquals

internal class PolynomialTest {
    private val term1 = Term(2.0,Variable(0, 2.0))
    private val term2 = Term(4.0, Variable(0, 1.0))
    private val term3 = Term(4.0, Variable(0, 2.0))
    private val term4 = Term(3.0, Variable(0, 3.0))
    private val termNoVariable = Term(10.0, Variable(0, 0.0))
    private val polyNoVar = Polynomial(mutableListOf(termNoVariable))
    private val poly = Polynomial(mutableListOf(term1, term2, term3, term4))
    @Test
    operator fun invoke() {
        println(poly ( mapOf(
            Pair(0, 4.0),
            Pair(1, 2.0)
        ) )
        )
    }

    @Test
    fun testInvoke() {
        assertEquals( 10.0, polyNoVar () )
    }
    @Test
    fun plusAssign() {
        val term1 = Term(2.0,Variable(0, 2.0))
        val term2 = Term(4.0, Variable(0, 1.0))
        val term3 = Term(4.0, Variable(0, 2.0))
        val term4 = Term(3.0, Variable(0, 3.0))
        val poly = Polynomial(mutableListOf(term1, term2, term3, term4))
        val by = Polynomial(mutableListOf(term2, term1))
        println(poly)
        println(poly.Terms)
        poly += by
        println(poly)
    }
}