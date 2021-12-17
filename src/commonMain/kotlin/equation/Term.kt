package equation

@Suppress("unused")
class Term(): Comparable<Term> {
    var coefficient: Double = 1.0
    val totalExponent: Double get(){
        var out = 0.0
        for (i in Variables) {
            out+=i.exponent
        }
        return out
    }
    var Variables: MutableList<Variable> = mutableListOf()

    constructor(coefficient: Double, Variables: MutableList<Variable>) : this() {
        this.coefficient = coefficient
        this.Variables = Variables
        this.simplifyVariables()
    }
    constructor(coefficient: Double = 1.0) : this(coefficient, mutableListOf<Variable>())
    constructor(coefficient: Double = 1.0, exponent: Double = 1.0, variableId: Int = 0) : this(coefficient, mutableListOf(Variable(variableId,exponent)))
    constructor(coefficient: Double = 1.0, variable: Variable) : this(coefficient, mutableListOf(variable))

    operator fun times(other: Term): Term {
        val thisVars = this.Variables
        val otherVars = other.Variables
        val newVariables = mutableListOf<Variable>()
        var removed = 0
        for ((index, y) in thisVars.withIndex()) {
            val currentVar = Variable(y.id, y.exponent)
            for (x in index until otherVars.size) {
                val xVar = otherVars[x-removed]
                if (xVar.id==y.id) {
                    currentVar.exponent += xVar.exponent
                    otherVars.removeAt(x)
                    removed++
                }
            }
//            for (y2 in index until otherVars.size) {
//                val xVar = otherVars[x]
//                if (xVar.id==y.id) {
//                    currentVar.exponent += xVar.exponent
//                    thisVars.removeAt(index)
//                }
//            }
            newVariables.add(currentVar)
        }
        newVariables.addAll(otherVars)
        return Term(this.coefficient*other.coefficient, newVariables)
    }
    operator fun plus(other: Term): Term {
        if (!this.canAdd(other)) throw Throwable("Cannot Add")
        return Term(this.coefficient+other.coefficient, this.Variables)
    }
    operator fun minus(oterm: Term): Term {
return this
    }
    operator fun times(other: Polynomial): Polynomial = this.toPoly()*other
    operator fun div(other: Term): Term {
        val thisVars = this.Variables
        val otherVars = other.Variables

        for (thisVar in thisVars) {
            var hasId = false
            for (otherVar in otherVars) if (otherVar.id!=thisVar.id) hasId = false
            if (hasId) otherVars.add(Variable(thisVar.id, 0.0))
        }
        for (otherVar in otherVars) {
            for (thisVar in thisVars) if (thisVar.id!=otherVar.id) hasId = true
            val thisVar = thisVars.find{it.id==otherVar.id}
            if (thisVar!=null) thisVars[thisVars.indexOf(thisVar)] /= otherVar
            else thisVars.add(otherVar/Variable(otherVar.id, 0.0))
        }
        return Term(this.coefficient/other.coefficient, thisVars)
    }

//    operator fun div(other: Term): Term {
//        val out = Term()
//        out.coefficient = this.coefficient/other.coefficient
//        for (i in this.Variables) {
//            for (i in other.Variables) {
//
//            }
//        }
//    }

    override fun toString(): String {
        var r = if ((coefficient % 1.0) == 0.0) coefficient.toInt().toString() else coefficient.toString()
        for (i in Variables) {
            r+=i.toString()
        }
        return r
    }
    fun simplifyVariables() {
        // combine duplicates
        val allIds: MutableList<Int> = mutableListOf<Int>()
        for (i in this.Variables) {
            allIds.add(i.id)
        }
        for ((i, value) in allIds.withIndex()) {
            val matchingIds = allIds.subList(0,allIds.size).withIndex().filter { (index, num) -> num==value&&index>i}
            if (matchingIds.isNotEmpty()) {
                for ((dIn, duplicate) in matchingIds.withIndex()) {
                    this.Variables[i].exponent = this.Variables[i].exponent + this.Variables[duplicate.index-dIn].exponent
                    this.Variables.removeAt(duplicate.index-dIn)
                }
            }
        }
        //remove all unnecesary variables
        val toRemove = mutableListOf<Int>()
        for ((index, value) in this.Variables.withIndex()) if (value.exponent==0.0) toRemove.add(index)
        for ((i, value) in toRemove.withIndex()) this.Variables.removeAt(value-i)

        // final sort
        this.Variables.sort()
    }
    fun toPoly(): Polynomial = Polynomial(mutableListOf(this))
    fun canAdd(other: Term) = this.Variables == other.Variables
    fun equals(other: Any?): Boolean {
        if other.
        return (this.coefficient==other.coefficient) && (this.Variables==other.Variables)
    }
    override fun compareTo(other: Term): Int = this.totalExponent.compareTo(other.totalExponent)
}

