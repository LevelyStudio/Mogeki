package gg.levely.system.mogeki.codec

import gg.levely.system.mogeki.Component
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.component.BooleanComponent
import gg.levely.system.mogeki.component.BooleanComponentCodec
import gg.levely.system.mogeki.component.ByteArrayComponent
import gg.levely.system.mogeki.component.ByteArrayComponentCodec
import gg.levely.system.mogeki.component.DoubleComponent
import gg.levely.system.mogeki.component.DoubleComponentCodec
import gg.levely.system.mogeki.component.FloatComponent
import gg.levely.system.mogeki.component.FloatComponentCodec
import gg.levely.system.mogeki.component.IntComponent
import gg.levely.system.mogeki.component.IntComponentCodec
import gg.levely.system.mogeki.component.LongComponent
import gg.levely.system.mogeki.component.LongComponentCodec
import gg.levely.system.mogeki.component.StringComponent
import gg.levely.system.mogeki.component.StringComponentCodec

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