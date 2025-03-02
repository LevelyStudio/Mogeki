package gg.levely.system.mogeki

import java.util.concurrent.ConcurrentHashMap

class ComponentCodecRepository {

    private val codecs = ConcurrentHashMap<Key<out Component>, ComponentCodec<out Component, Any>>()
    private val keyByName = ConcurrentHashMap<String, Key<out Component>>()

    fun <T : Component> register(key: Key<T>, codec: ComponentCodec<T, *>) {
        require(!Component::class.java.isAssignableFrom(key.value.java)) { "Key must be a subclass of Component" }

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

    fun <T : Component> unregister(key: Key<T>) {
        codecs.remove(key)
        keyByName.values.removeIf { it == key }
    }
}

interface ComponentCodec<I : Component, O> {
    fun marshal(component: I): O
    fun unMarshal(output: O): I
}
