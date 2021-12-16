package graphing

import kotlin.test.Test


internal class GraphTest {
    @Test
    fun Graph() {
        var graph = graphing.Graph(mutableListOf(graphing.Line(1.0,1.0)))
        graph.Graph()
        graph.print()
    }
}