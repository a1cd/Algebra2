package graphing

import equation.Polynomial
import equation.Term
import equation.Variable
import kotlin.test.Test


internal class GraphTest {
    private val term1 = Term(1.0, Variable(0, 2.0))

    private val poly = Polynomial(term1)
    @Test
    fun graph() {
        //        graph.Graph()
//        println(graph.equations[0].polynomial)
//        graph.print()

        val graph = Graph(mutableListOf(Equasion(poly)))
        graph.Graph(5, 15)
        graph.print()
    }
}