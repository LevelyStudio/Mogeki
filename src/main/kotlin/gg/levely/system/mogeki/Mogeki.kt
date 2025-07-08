package gg.levely.system.mogeki

import gg.levely.system.mogeki.codec.ComponentCodecRepository

class Mogeki(val mongoDriver: MongoDriver) {

    private val mogekiCollections = mutableMapOf<String, MogekiCollection>()
    val componentCodecRepository = ComponentCodecRepository()

    @JvmOverloads
    fun getMogekiCollection(name: String, customComponentCodecRepository: ComponentCodecRepository = componentCodecRepository): MogekiCollection {
        return mogekiCollections.getOrPut(name) {
            MogekiCollection(name, mongoDriver, customComponentCodecRepository)
        }
    }

}