package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class BooleanComponent(private val value: Boolean) : ValuableComponent<Boolean> {

    override fun getValue(): Boolean = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BooleanComponent

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

object BooleanComponentCodec : ComponentCodec<BooleanComponent, Boolean> {

    override fun marshal(component: BooleanComponent): Boolean = component.getValue()

    override fun unMarshal(output: Boolean): BooleanComponent = BooleanComponent(output)

}

fun <T : ValuableComponent<Boolean>> Entity.setComponent(key: Key<T>, value: Boolean) {
    setComponent(key, BooleanComponent(value) as T)
}