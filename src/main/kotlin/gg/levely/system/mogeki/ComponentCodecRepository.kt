package gg.levely.system.mogeki

class ComponentCodecRepository {

    private val codecs = mutableMapOf<Key<out Component>, ComponentCodec<out Component, Any>>()
    private val keyByName = mutableMapOf<String, Key<out Component>>()

    fun <T : Component> register(key: Key<T>, codec: ComponentCodec<T, *>) {
        require(key.value != Component::class) { "Key must be a subclass of Component" }

        codecs[key] = codec as ComponentCodec<out Component, Any>
        keyByName[key.name] = key
    }

    fun <T : Component> getCodec(key: Key<T>): ComponentCodec<T, Any>? {
        return codecs[key] as? ComponentCodec<T, Any>
    }

    fun getCodecByName(name: String): ComponentCodec<Component, Any>? {
        return keyByName[name]?.let { key ->
            codecs[key]
        } as? ComponentCodec<Component, Any>
    }

    fun <T : Component> getKey(name: String): Key<T>? {
        return keyByName[name] as? Key<T>
    }


}

interface ComponentCodec<I : Component, O> {

    fun marshal(component: I): O

    fun unMarshal(output: O): I

}