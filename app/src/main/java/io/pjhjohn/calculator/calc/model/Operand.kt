package io.pjhjohn.calculator.calc.model

sealed class Operand {

    class Fresh(val number: Float) : Operand()
    class Dirty(val number: Int) : Operand()
    object Empty : Operand()

    val isFresh: Boolean
        get() = this is Fresh

    val isDirty: Boolean
        get() = this is Dirty

    val isEmpty: Boolean
        get() = this is Empty

    val value: Float
        get() = when (this) {
            is Fresh -> this.number
            is Dirty -> this.number.toFloat()
            is Empty -> throw IllegalArgumentException("Operand is empty")
        }

    fun update(number: Int): Operand =
        when (this) {
            is Fresh -> Dirty(number)
            is Dirty -> Dirty(this.number * 10 + number)
            is Empty -> Dirty(number)
        }

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
