package equation


class Variable(var id: Int = 0, var exponent: Double = 1.0):Comparable<Variable> {
    override fun toString(): String {
        val chars: List<Char> = listOf('x', 'y', 'z', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k', 'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a')
        var currentReturn = ""
        currentReturn += if (id <= chars.size-1) {
            chars[id]
        } else {
            "(#$id)"
        }
        if (exponent==0.0) return ""
        else if (exponent!=1.0) currentReturn+=Constants.convert(exponent)
        return currentReturn
    }
    operator fun times(other: Variable): Variable {
        if (this.id != other.id) throw Throwable("Variable id mismatch")
        return Variable(this.id, this.exponent+other.exponent)
    }
    operator fun div(other: Variable): Variable {
        if (this.id != other.id) throw Throwable("Variable id mismatch")
        return Variable(this.id, this.exponent - other.exponent)
    }
    override fun equals(other: Any?): Boolean {
        val otherVariable = other as? Variable
        if (otherVariable != null) return (this.exponent == otherVariable.exponent) && (this.id == otherVariable.id)
        return false
    }

    override fun compareTo(other: Variable): Int {
        if ((this.exponent.compareTo(other.exponent)) != 0) return this.exponent.compareTo(other.exponent)
        else return this.id.compareTo(other.id)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + exponent.hashCode()
        return result
    }
}

