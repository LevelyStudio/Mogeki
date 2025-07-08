package gg.levely.system.mogeki.codec

import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.component.*

fun ComponentCodecRepository.registerString(key: Key<StringComponent>) {
    register(key, StringComponentCodec)
}

fun ComponentCodecRepository.registerInt(key: Key<IntComponent>) {
    register(key, IntComponentCodec)
}

fun ComponentCodecRepository.registerDouble(key: Key<DoubleComponent>) {
    register(key, DoubleComponentCodec)
}

fun ComponentCodecRepository.registerFloat(key: Key<FloatComponent>) {
    register(key, FloatComponentCodec)
}

fun ComponentCodecRepository.registerBoolean(key: Key<BooleanComponent>) {
    register(key, BooleanComponentCodec)
}

fun ComponentCodecRepository.registerByteArray(key: Key<ByteArrayComponent>) {
    register(key, ByteArrayComponentCodec)
}

fun ComponentCodecRepository.registerLong(key: Key<LongComponent>) {
    register(key, LongComponentCodec)
}

fun ComponentCodecRepository.registerRawDocument(key: Key<RawDocumentComponent>) {
    register(key, RawDocumentComponentCodec)
}

fun ComponentCodecRepository.registerEntity(key: Key<EntityComponent>) {
    registerEntity(key, this)
}

fun ComponentCodecRepository.registerEntity(key: Key<EntityComponent>, componentCodecRepository: ComponentCodecRepository) {
    register(key, EntityComponentCodec(componentCodecRepository))
}