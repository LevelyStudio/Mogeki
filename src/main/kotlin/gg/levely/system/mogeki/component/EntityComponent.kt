package gg.levely.system.mogeki.component

import gg.levely.system.mogeki.Component
import gg.levely.system.mogeki.Entity
import gg.levely.system.mogeki.codec.ComponentCodec
import gg.levely.system.mogeki.codec.ComponentCodecRepository
import org.bson.Document
import kotlin.collections.component1
import kotlin.collections.component2

class EntityComponent(private val value: Entity) : ValuableComponent<Entity> {

    override fun getValue(): Entity = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EntityComponent

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}

class EntityComponentCodec(val componentCodecRepository: ComponentCodecRepository) : ComponentCodec<EntityComponent, Document> {

    override fun marshal(component: EntityComponent): Document {
        val document = Document()
        val entity = component.getValue()

        entity.getComponents().forEach { (key, value) ->
            val componentCodec =
                componentCodecRepository.getCodec(key) as? ComponentCodec<Component, Any> ?: return@forEach
            document[key.name] = componentCodec.marshal(value)
        }

        return document
    }

    override fun unMarshal(output: Document): EntityComponent {
        val entity = Entity()

        output.forEach { (key, value) ->
            val componentCodec = componentCodecRepository.getCodecByName(key)
            val component = componentCodec?.unMarshal(value) ?: return@forEach
            val lKey = componentCodecRepository.getKey<Component>(key) ?: return@forEach
            entity.setComponent(lKey, component)
        }

        return EntityComponent(entity)
    }
}