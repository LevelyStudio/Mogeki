package gg.levely.system.mogeki

class Entity {

    val components = mutableMapOf<Key<out Component>, Component>()

    fun <T : Component> addComponent(key: Key<T>, component: T) {
        components[key] = component
    }

    fun <T : Component> getComponent(key: Key<T>): T? {
        return components[key] as? T
    }

    fun <T : Component> hasComponent(key: Key<T>): Boolean {
        return components.containsKey(key)
    }

    fun <T : Component> removeComponent(key: Key<T>) {
        components.remove(key)
    }

}