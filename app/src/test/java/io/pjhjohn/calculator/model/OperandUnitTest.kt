package io.pjhjohn.calculator.model

import io.pjhjohn.calculator.UnitTestWatcher
import org.assertj.core.api.Assertions.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.*

class OperandUnitTest : UnitTestWatcher() {

    private val random by lazy { Random() }

    @Before
    fun before() {

    }

    @After
    fun after() {

    }

    @Test
    fun testFreshOperand() {
        (1..20).map { random.nextFloat() }.forEach { floatVal ->
            Operand.Fresh(floatVal).let {
                assertThat(it).isInstanceOf(Operand.Fresh::class.java)
                assertThat(it.isEmpty).isFalse()
                assertThat(it.number).isEqualTo(floatVal)
                assertThat(it.value).isEqualTo(floatVal)
            }
        }
    }

    @Test
    fun testDirtyOperand() {
        (1..20).map { random.nextInt(10) }.forEach { intVal ->
            Operand.Dirty(intVal).let {
                assertThat(it).isInstanceOf(Operand.Dirty::class.java)
                assertThat(it.isEmpty).isFalse()
                assertThat(it.number).isEqualTo(intVal)
                assertThat(it.value).isEqualTo(intVal.toFloat())
            }
        }
    }

    @Test
    fun testEmptyOperand() {
        Operand.Empty.let {
            assertThat(it).isInstanceOf(Operand.Empty::class.java)
            assertThat(it.isEmpty).isTrue()
            assertThatIllegalArgumentException().isThrownBy { Operand.Empty.value }
        }
    }

    @Test
    fun testToString() {
        assertThat(Operand.Fresh(0.0f).toString()).isEqualTo("0")
        assertThat(Operand.Fresh(0.1f).toString()).isEqualTo("0.1")
        assertThat(Operand.Fresh(1.0f).toString()).isEqualTo("1")
        assertThat(Operand.Fresh(1.1f).toString()).isEqualTo("1.1")
        assertThat(Operand.Fresh(-0.0f).toString()).isEqualTo("0")
        assertThat(Operand.Fresh(-0.1f).toString()).isEqualTo("-0.1")
        assertThat(Operand.Fresh(-1.0f).toString()).isEqualTo("-1")
        assertThat(Operand.Fresh(-1.1f).toString()).isEqualTo("-1.1")
        assertThat(Operand.Dirty(0).toString()).isEqualTo("0")
        assertThat(Operand.Dirty(1).toString()).isEqualTo("1")
        assertThat(Operand.Dirty(-1).toString()).isEqualTo("-1")
        assertThat(Operand.Empty.toString()).isEmpty()
    }

    @Test
    fun testUpdate() {
        val fresh = Operand.Fresh(1.0f)
        val dirty = Operand.Dirty(1)
        val empty = Operand.Empty

        assertThatIllegalArgumentException().isThrownBy { fresh.update(fresh) }
        assertThat(fresh.update(dirty)).isEqualTo(dirty)
        assertThatIllegalArgumentException().isThrownBy { fresh.update(empty) }

        assertThatIllegalArgumentException().isThrownBy { dirty.update(fresh) }
        assertThat(dirty.update(dirty)).isEqualToComparingFieldByField(Operand.Dirty(11))
        assertThatIllegalArgumentException().isThrownBy { dirty.update(empty) }

        assertThatIllegalArgumentException().isThrownBy { empty.update(fresh) }
        assertThat(empty.update(dirty)).isEqualTo(dirty)
        assertThatIllegalArgumentException().isThrownBy { empty.update(empty) }
    }

}
