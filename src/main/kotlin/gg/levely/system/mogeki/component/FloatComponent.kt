package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class FloatComponent(private val value: Float) : ValuableComponent<Float> {

    override fun getValue() = value

}

object FloatComponentCodec : ComponentCodec<FloatComponent, Double> {

    override fun marshal(component: FloatComponent) = component.getValue().toDouble()

    override fun unMarshal(output: Double) = FloatComponent(output.toFloat())

}

fun <T : ValuableComponent<Float>> Entity.setComponent(key: Key<T>, value: Float) {
    setComponent(key, FloatComponent(value) as T)
}