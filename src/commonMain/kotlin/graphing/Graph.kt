package graphing

class Graph(var equations: MutableList<Equasion>) {

    var pointArray: MutableList<MutableList<Boolean>>? = null
    fun convertPointArrayToString(): String {
        if (pointArray == null) return ""
        var out = ""
        for (x in pointArray!!) {
            for (y in x) {
                out += if (y) "██" else " ."
            }
            out+="\n"
        }
        return out
    }
    fun Graph(height: Int = 4, width: Int = 5, lineWidth: Double=1.5, xVarId: Int = 0, yVarId: Int = 1) {
        var points = mutableListOf<MutableList<Boolean>>()
        for (x in -height..height) {
            points.add(mutableListOf())
            for (y in -width..width) {
                var isPoint = false
                for (eq in equations) {
                    var value = eq.polynomial ( mapOf(Pair(xVarId, x.toDouble()), Pair(yVarId, y.toDouble())))
                    if ((value >= (y - lineWidth)) && (value < (y + lineWidth))) {
                        isPoint = true
                        break
                    }
                }
                points[x+height].add(isPoint)
            }
        }
        this.pointArray = points
    }

    fun print() {
        if (pointArray == null) {
            this.Graph()
        }
        println(this.convertPointArrayToString())
    }
}