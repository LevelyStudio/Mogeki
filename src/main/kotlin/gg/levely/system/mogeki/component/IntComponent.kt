package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class IntComponent(private val value: Int) : ValuableComponent<Int> {

    override fun getValue() = value

}

object IntComponentCodec : ComponentCodec<IntComponent, Int> {

    override fun marshal(component: IntComponent) = component.getValue()

    override fun unMarshal(output: Int) = IntComponent(output)

}

fun <T : ValuableComponent<Int>> Entity.setComponent(key: Key<T>, value: Int) {
    setComponent(key, IntComponent(value) as T)
}