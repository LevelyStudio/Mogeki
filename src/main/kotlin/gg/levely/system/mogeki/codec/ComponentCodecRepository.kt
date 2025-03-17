package gg.levely.system.mogeki.codec

import gg.levely.system.mogeki.Component
import gg.levely.system.mogeki.Key
import java.util.concurrent.ConcurrentHashMap

class ComponentCodecRepository {

    private val codecs = ConcurrentHashMap<Key<out Component>, ComponentCodec<out Component, Any>>()
    private val keyByName = ConcurrentHashMap<String, Key<out Component>>()

    fun <T : Component> register(key: Key<T>, codec: ComponentCodec<T, *>) {
        require(!key.value.java.isAssignableFrom(Component::class.java)) { "${key.value} is not a subclass of Component" }

        codecs[key] = codec as ComponentCodec<out Component, Any>
        keyByName[key.name] = key
    }

    fun <T : Component> getCodec(key: Key<T>): ComponentCodec<T, Any> {
        return codecs[key] as? ComponentCodec<T, Any>
            ?: throw NoSuchElementException("No codec found for key: ${key.name}")
    }

    fun getCodecByName(name: String): ComponentCodec<Component, Any> {
        val key = keyByName[name]
            ?: throw NoSuchElementException("No key found for name: $name")
        return codecs[key] as? ComponentCodec<Component, Any>
            ?: throw NoSuchElementException("No codec found for key: $name")
    }

    fun <T : Component> getKey(name: String): Key<T> {
        return keyByName[name] as? Key<T>
            ?: throw NoSuchElementException("No key found for name: $name")
    }

    fun  getKeyOrNull(name: String): Key<*>? {
        return keyByName[name]
    }

    fun <T : Component> unregister(key: Key<T>) {
        codecs.remove(key)
        keyByName.values.removeIf { it == key }
    }

    fun getRegisteredKeys(): Set<Key<out Component>> {
        return codecs.keys
    }
}

interface ComponentCodec<I : Component, O> {

    fun marshal(component: I): O

    fun unMarshal(output: O): I

}
