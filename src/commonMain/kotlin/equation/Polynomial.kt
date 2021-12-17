package equation

import kotlin.math.pow

class Polynomial() {
    constructor(Terms: MutableList<Term> = mutableListOf()) : this() {
        this.Terms = Terms
        this.simplifyTerms()
    }
    constructor(Term: Term): this(mutableListOf(Term))
    var Terms: MutableList<Term> = mutableListOf()
    operator fun times(by: Polynomial): Polynomial {
        val out = Polynomial()
        for (x in 0 until this.Terms.size) {
            for (y in x until by.Terms.size) {
                out.addTerm(this.Terms[x] * by.Terms[y])
            }
        }
        return out
    }
    operator fun plusAssign(other: Polynomial) {
        for (oterm in other.Terms) {
            val term = this.Terms.withIndex().find { (_, term) -> term.Variables == oterm.Variables }
            if (term != null) this.Terms[term.index] += oterm
            else this.addTerm(oterm)
        }
        this.simplifyTerms()
    }
    operator fun plus(other: Polynomial): Polynomial {
        var new = this
        new.plusAssign(other)
        return new
    }
    operator fun minus(other: Polynomial): Polynomial {
        var new = this
        for (oterm in new.Terms) {
            val term = new.Terms.withIndex().find { (_, term) -> term.Variables == oterm.Variables }
            if (term != null) new.Terms[term.index] -= oterm
            else new.addTerm(oterm)
        }
        new.simplifyTerms()
        return new
    }
    operator fun div(by: Polynomial) {
        val out = Polynomial()
        val bringDown = Polynomial()
        if (by.Terms.firstOrNull() == null) {
            throw Exception("no terms in polynomial")
        }
        var currentBy: Polynomial = by
        for (i in this.Terms.sortedDescending()) {
            currentBy.simplifyTerms()
            val div = i/currentBy.Terms.first()
            currentBy -= div*by
        }
    }
    operator fun invoke(variables: Map<Int, Double>): Double {
        var total = 0.0
        for (term in this.Terms) {
            var multiplied = term.coefficient
            for (variable in term.Variables) {
                if (variables[variable.id] != null) multiplied *=  variables[variable.id]!!.pow(variable.exponent)
            }
            total+=multiplied
        }
        return total
    }
    operator fun invoke(): Double = invoke(mapOf())
    fun addTerm(newTerm: Term) {
        var addedTermNormally = false
        for ((i, term) in this.Terms.withIndex()) {
            if (term.canAdd(newTerm)) {
                this.Terms[i] = term + newTerm
                addedTermNormally=true
            }
            break
        }
        if (!addedTermNormally) this.Terms.add(newTerm)
        this.simplifyTerms()
    }
    fun simplifyTerms() {
        this.Terms.sortDescending()
        var allIds: MutableList<MutableList<Variable>> = mutableListOf<MutableList<Variable>>()
        for (i in this.Terms) {
            i.simplifyVariables()
            allIds.add(i.Variables)
        }
        for ((i, value) in allIds.withIndex()) {
            val matchingIds = allIds.withIndex().filter { (index, num) -> num==value&&index>i}
            println(matchingIds)
            if (matchingIds.isNotEmpty()) {
                println("hi2")
                for ((dIn, duplicate) in matchingIds.withIndex()) {
                    println("hi3")
                    this.Terms[i] = this.Terms[i] + this.Terms[duplicate.index-dIn]
                    this.Terms.removeAt(duplicate.index-dIn)
                }
            }
        }
    }
    operator fun times(other: Term): Polynomial = other.toPoly()*this
    override fun toString(): String {
        var string = ""
        for ((index, i) in this.Terms.withIndex()) {
            string += if (index != 0) if (i.coefficient>0) "+" else "-" else ""
            string += i.toString()
        }
        return string
    }
}