package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec

class StringComponent(private val value: String) : ValuableComponent<String> {

    override fun getValue() = value

}

object StringComponentCodec : ComponentCodec<StringComponent, String> {

    override fun marshal(component: StringComponent) = component.getValue()

    override fun unMarshal(output: String) = StringComponent(output)

}

fun <T : ValuableComponent<String>> Entity.setComponent(key: Key<T>, value: String) {
    setComponent(key, StringComponent(value) as T)
}