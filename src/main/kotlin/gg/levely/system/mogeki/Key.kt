package gg.levely.system.mogeki


import kotlin.reflect.KClass

interface Key<T : Component> {
    val name: String
    val value: KClass<T>
}

class KeyImpl<T : Component> (
    override val name: String,
    override val value: KClass<T>
) : Key<T>


inline fun <reified T : Component> key(name: String): Key<T> {
    return KeyImpl(name, T::class)
}