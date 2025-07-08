package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec
import org.bson.types.Binary

class ByteArrayComponent(private val value: ByteArray) : ValuableComponent<ByteArray> {

    override fun getValue() = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ByteArrayComponent

        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        return value.contentHashCode()
    }

}

object ByteArrayComponentCodec : ComponentCodec<ByteArrayComponent, Any> {

    override fun marshal(component: ByteArrayComponent) = component.getValue()

    override fun unMarshal(output: Any): ByteArrayComponent {
        if (output !is Binary) throw IllegalArgumentException("Output is not a Binary")
        return ByteArrayComponent(output.data)
    }

}

fun <T : ValuableComponent<ByteArray>> Entity.setComponent(key: Key<T>, value: ByteArray) {
    setComponent(key, ByteArrayComponent(value) as T)
}