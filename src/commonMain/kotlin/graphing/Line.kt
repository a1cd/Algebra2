package graphing

import equation.Polynomial
import equation.Term
import equation.Variable

@Suppress("unused")
class Line(xInt: Double, yInt: Double) :
    Equasion(Polynomial(mutableListOf(Term((yInt/-xInt), Variable(0)))))