package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Component
import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class DoubleComponent(private val value: Double) : ValuableComponent<Double> {

    override fun getValue() = value

}

object DoubleComponentCodec : ComponentCodec<DoubleComponent, Double> {

    override fun marshal(component: DoubleComponent) = component.getValue()

    override fun unMarshal(output: Double) = DoubleComponent(output)

}

fun <T: ValuableComponent<Double>> Entity.setComponent(key: Key<T>, value: Double) {
    setComponent(key, DoubleComponent(value) as T)
}