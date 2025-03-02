package gg.levely.system.mogeki

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.CreateIndexOptions
import com.mongodb.client.model.IndexModel
import com.mongodb.client.model.Projections
import com.mongodb.client.model.UpdateOptions
import org.bson.Document
import org.bson.conversions.Bson

class MogekiCollection(
    name: String,
    mongoDriver: MongoDriver,
    private val componentCodecRepository: ComponentCodecRepository,
) {

    val database = mongoDriver.getDatabase()
    val collection: MongoCollection<Document> = database.getCollection(name)

    @JvmOverloads
    fun createIndex(index: IndexModel, indexOptions: CreateIndexOptions = CreateIndexOptions()) {
        collection.createIndexes(listOf(index), indexOptions)
    }

    fun getEntity(filter: Bson): Entity? {
        val document = collection.find(filter).firstOrNull() ?: return null
        val entity = Entity()
        document.forEach { (key, value) ->
            val componentCodec = componentCodecRepository.getCodecByName(key) ?: return@forEach
            val component = componentCodec.unMarshal(value)
            entity.setComponent(componentCodecRepository.getKey(key)!!, component)
        }

        return entity
    }

    fun getEntity(filter: Bson, vararg keys: Key<out Component>): Entity? {
        val document = collection.find(filter)
            .projection(Projections.fields(Projections.include(keys.map { it.name }), Projections.excludeId()))
            .firstOrNull() ?: return null

        val entity = Entity()
        keys.forEach { key ->
            val componentCodec = componentCodecRepository.getCodec(key) ?: return@forEach
            val value = document[key.name] ?: return@forEach
            val component: Component = componentCodec.unMarshal(value)
            entity.setComponent(key as Key<Component>, component)
        }

        return entity
    }

    fun <T : Component> getComponent(filter: Bson, key: Key<T>): T? {
        val componentCodec = componentCodecRepository.getCodec(key) ?: return null
        val value = collection.find(filter)
            .projection(Projections.fields(Projections.include(key.name), Projections.excludeId()))
            .firstOrNull()
            ?.get(key.name) ?: return null

        return componentCodec.unMarshal(value)
    }

    fun <T : Component> upsertComponent(filter: Bson, key: Key<T>, component: T) {
        val componentCodec = componentCodecRepository.getCodec(key) ?: return
        val value = componentCodec.marshal(component)
        collection.updateOne(filter, Document("\$set", Document(key.name, value)), UpdateOptions().upsert(true))
    }

    fun upsertEntity(filter: Bson, entity: Entity) {
        val document = Document()
        entity.getComponents().forEach { (key, value) ->
            val componentCodec =
                componentCodecRepository.getCodec(key) as? ComponentCodec<Component, Any> ?: return@forEach
            document[key.name] = componentCodec.marshal(value)
        }

        collection.updateOne(filter, Document("\$set", document), UpdateOptions().upsert(true))
    }

}