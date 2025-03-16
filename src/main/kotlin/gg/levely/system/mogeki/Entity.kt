package gg.levely.system.mogeki

import java.util.concurrent.ConcurrentHashMap

class Entity {

    private val components = ConcurrentHashMap<Key<out Component>, Component>()

    fun <T : Component> setComponent(key: Key<T>, component: T) {
        components[key] = component
    }

    fun <T : Component> getComponent(key: Key<T>): T? {
        return components[key] as? T
    }

    fun <T : Component> requireComponent(key: Key<T>): T {
        return components[key] as? T
            ?: throw NoSuchElementException("No component found for key: ${key.name}")
    }

    fun <T : Component> hasComponent(key: Key<T>): Boolean {
        return components.containsKey(key)
    }

    fun <T : Component> removeComponent(key: Key<T>): Boolean {
        return components.remove(key) != null
    }

    fun getComponents(): Map<Key<out Component>, Component> {
        return components.toMap()
    }

}
