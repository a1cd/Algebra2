package equation

import kotlin.test.Test
import kotlin.test.assertEquals


internal class TermTest {
    @Test
    fun test() {
        val term1 = Term(2.0,Variable(0, 0.0))
        val term2 = Term(4.0, Variable(0, 1.0))
        val term3 = Term(4.0, Variable(0, 2.0))
        val term4 = Term(3.0, Variable(0, 3.0))
        println("$term1 * $term2 = ${term1 * term2}");
        println(term2)
        println(term3)
        println("the thing: "+ (term1.toPoly() * term2 * term3))

        val poly = Polynomial(mutableListOf(term1, term2, term3, term4))
        val by = Polynomial(mutableListOf(term2, term1))
        println(Term(2.0, mutableListOf(Variable(0), Variable(0))))
        println(poly/by)
    }
    @Test
    fun test2() {
        val t1 = Term(4.0, mutableListOf(Variable(1,2.0),Variable(0,2.0)))
        val t2 = Term(2.0, mutableListOf(Variable(1,5.0),Variable(0,3.0),Variable(2,1.0)))
        assertEquals("2y⁻³x⁻¹z", (t1/t2).toString())

    }
}
