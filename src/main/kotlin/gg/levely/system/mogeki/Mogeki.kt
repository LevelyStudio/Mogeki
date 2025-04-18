package gg.levely.system.mogeki

import gg.levely.system.mogeki.codec.ComponentCodecRepository

class Mogeki(val mongoDriver: MongoDriver) {

    private val mogekiCollections = mutableMapOf<String, MogekiCollection>()
    val componentCodecRepository = ComponentCodecRepository()

    fun getMogekiCollection(name: String): MogekiCollection {
        return mogekiCollections.getOrPut(name) {
            MogekiCollection(name, mongoDriver, componentCodecRepository)
        }
    }

}