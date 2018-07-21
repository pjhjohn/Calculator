package io.pjhjohn.calculator.model

sealed class Operand {

    class Fresh(val number: Float) : Operand()
    class Dirty(val number: Int) : Operand()
    object Empty : Operand()

    val isEmpty: Boolean
        get() = this is Empty

    val value: Float
        get() = when (this) {
            is Fresh -> this.number
            is Dirty -> this.number.toFloat()
            is Empty -> throw IllegalArgumentException("Operand is empty")
        }

    fun update(operand: Operand): Operand =
        if (operand is Dirty) when (this) {
            is Fresh -> operand
            is Dirty -> Dirty(this.number * 10 + operand.number)
            is Empty -> operand
        }
        else throw IllegalArgumentException("Operand is not dirty")

    override fun toString(): String =
        when (this) {
            is Fresh -> when {
                this.number - this.number.toInt() == 0.0f -> this.number.toInt().toString()
                else -> this.number.toString()
            }
            is Dirty -> this.number.toString()
            is Empty -> ""
        }

}
