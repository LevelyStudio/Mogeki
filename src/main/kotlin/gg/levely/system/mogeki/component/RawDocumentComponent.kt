package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.codec.ComponentCodec
import org.bson.Document

class RawDocumentComponent(private val value: Document) : ValuableComponent<Document> {

    override fun getValue(): Document = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RawDocumentComponent

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

object RawDocumentComponentCodec : ComponentCodec<RawDocumentComponent, Document> {

    override fun marshal(component: RawDocumentComponent): Document {
        return component.getValue()
    }

    override fun unMarshal(output: Document): RawDocumentComponent {
        return RawDocumentComponent(output)
    }

}

fun <T : ValuableComponent<Document>> Entity.setComponent(key: Key<T>, value: Document) {
    setComponent(key, RawDocumentComponent(value) as T)
}

