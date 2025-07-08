package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class StringComponent(private val value: String) : ValuableComponent<String> {

    override fun getValue() = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StringComponent

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

object StringComponentCodec : ComponentCodec<StringComponent, String> {

    override fun marshal(component: StringComponent) = component.getValue()

    override fun unMarshal(output: String) = StringComponent(output)

}

fun <T : ValuableComponent<String>> Entity.setComponent(key: Key<T>, value: String) {
    setComponent(key, StringComponent(value) as T)
}