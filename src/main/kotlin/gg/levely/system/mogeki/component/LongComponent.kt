package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class LongComponent(val value: Long) : ValuableComponent<Long> {

    override fun getValue(): Long {
        return value
    }

}

object LongComponentCodec : ComponentCodec<LongComponent, Long> {

    override fun marshal(component: LongComponent): Long {
        return component.getValue()
    }

    override fun unMarshal(output: Long): LongComponent {
        return LongComponent(output)
    }

}

fun <T : ValuableComponent<Long>> Entity.setComponent(key: Key<T>, value: Long) {
    setComponent(key, LongComponent(value) as T)
}