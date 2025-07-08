package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class BooleanComponent(private val value: Boolean) : ValuableComponent<Boolean> {
    override fun getValue(): Boolean = value
}

object BooleanComponentCodec : ComponentCodec<BooleanComponent, Boolean> {

    override fun marshal(component: BooleanComponent): Boolean = component.getValue()

    override fun unMarshal(output: Boolean): BooleanComponent = BooleanComponent(output)

}

fun <T : ValuableComponent<Boolean>> Entity.setComponent(key: Key<T>, value: Boolean) {
    setComponent(key, BooleanComponent(value) as T)
}