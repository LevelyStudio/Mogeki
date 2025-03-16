package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Component
import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.MogekiCollection
import org.bson.conversions.Bson

interface ValuableComponent<T> : Component {

    fun getValue(): T

}

inline fun <reified T : ValuableComponent<R>, reified R> Entity.getValuableComponent(key: Key<T>): R? {
    val component = getComponent(key)
    return (component as? ValuableComponent<R>)?.getValue()
}