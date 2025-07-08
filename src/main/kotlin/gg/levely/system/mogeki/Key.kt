package gg.levely.system.mogeki


import gg.levely.system.mogeki.component.*
import kotlin.reflect.KClass

interface Key<T : Component> {
    val name: String
    val value: KClass<T>
}

class KeyImpl<T : Component>(
    override val name: String,
    override val value: KClass<T>,
) : Key<T>


inline fun <reified T : Component> key(name: String): Key<T> {
    return KeyImpl(name, T::class)
}

fun keyString(name: String): Key<StringComponent> = key(name)

fun keyBoolean(name: String): Key<BooleanComponent> = key(name)

fun keyInt(name: String): Key<IntComponent> = key(name)

fun keyLong(name: String): Key<LongComponent> = key(name)

fun keyFloat(name: String): Key<FloatComponent> = key(name)

fun keyDouble(name: String): Key<DoubleComponent> = key(name)

fun keyByteArray(name: String): Key<ByteArrayComponent> = key(name)
