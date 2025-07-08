package gg.levely.system.mogeki

import gg.levely.system.mogeki.component.ValuableComponent
import org.bson.conversions.Bson

inline fun <reified T : ValuableComponent<R>, reified R> MogekiCollection.getValuableComponent(
    filter: Bson,
    key: Key<T>,
): R? {
    val component = getComponent(filter, key)
    return (component as? ValuableComponent<R>)?.getValue()
}