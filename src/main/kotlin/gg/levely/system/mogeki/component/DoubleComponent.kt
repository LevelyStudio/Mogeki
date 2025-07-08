package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class DoubleComponent(private val value: Double) : ValuableComponent<Double> {

    override fun getValue() = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DoubleComponent

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

object DoubleComponentCodec : ComponentCodec<DoubleComponent, Double> {

    override fun marshal(component: DoubleComponent) = component.getValue()

    override fun unMarshal(output: Double) = DoubleComponent(output)

}

fun <T : ValuableComponent<Double>> Entity.setComponent(key: Key<T>, value: Double) {
    setComponent(key, DoubleComponent(value) as T)
}