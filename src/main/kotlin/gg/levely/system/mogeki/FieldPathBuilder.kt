package gg.levely.system.mogeki

import kotlin.reflect.KProperty1

class FieldPathBuilder<T> {
    private val pathSegments = mutableListOf<String>()

    fun <R> field(property: KProperty1<T, R>, init: (FieldPathBuilder<R>.() -> Unit)? = null) {
        pathSegments.add(property.name)
        init?.let {
            val subBuilder = FieldPathBuilder<R>()
            subBuilder.it()
            pathSegments.addAll(subBuilder.pathSegments)
        }
    }

    fun build(): String = pathSegments.joinToString(".")
}

inline fun <reified T> fieldPath(init: FieldPathBuilder<T>.() -> Unit): String {
    val builder = FieldPathBuilder<T>()
    builder.init()
    return builder.build()
}
