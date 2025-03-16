import com.mongodb.client.model.Filters
import gg.levely.system.mogeki.Component
import gg.levely.system.mogeki.codec.ComponentCodec
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.Mogeki
import gg.levely.system.mogeki.component.StringComponent
import gg.levely.system.mogeki.component.getValuableComponent
import gg.levely.system.mogeki.fieldPath
import gg.levely.system.mogeki.key
import org.bson.conversions.Bson
import kotlin.reflect.KProperty1

class MogekiTest {

    lateinit var mogeki: Mogeki

    val NAME_COMPONENT = key<NameComponent>("name")
    val TYPE_COMPONENT = key<TypeComponent>("type")
    val TEST = key<StringComponent>("hello")

    init {
        val componentCodecRepository = mogeki.componentCodecRepository
        componentCodecRepository.register(NAME_COMPONENT, NameComponentCodec())

        val mogekiCollection = mogeki.getMogekiCollection("test")
        val entity = mogekiCollection.getEntity(TYPE_COMPONENT.filter("foo"))
        var value = entity?.getValuableComponent(TEST)

        println(entity)


        val fieldPath = fieldPath<TypeComponent> {
            field(TypeComponent::origin) {
                field(OriginComponent::origin)
            }
        }

        println(fieldPath)
    }
}

class NameComponent(
    val name: String,
) : Component


data class TypeComponent(
    var type: String,
    var origin: OriginComponent
) : Component

data class OriginComponent(
    var origin: String,
) : Component


class NameComponentCodec : ComponentCodec<NameComponent, String> {

    override fun marshal(component: NameComponent): String {
        return component.name
    }

    override fun unMarshal(output: String): NameComponent {
        return NameComponent(output)
    }
}

fun getDeepFieldPath(key: Key<*>, vararg properties: KProperty1<*, *>): String {
    if (properties.isEmpty()){
        return key.name
    }

    return "${key.name}.${properties.joinToString(".") { it.name }}"
}

fun <T> getDeepFieldPath(property: KProperty1<T, *>): String {
    val pathSegments = mutableListOf<String>()
    var current: KProperty1<*, *>? = property

    while (current != null) {
        pathSegments.add(current.name)
        current = (current as? KProperty1<Any, *>)?.getter?.returnType?.classifier as? KProperty1<*, *>
    }

    return pathSegments.reversed().joinToString(".")
}


fun Key<TypeComponent>.filter(type: String): Bson {
    return Filters.eq(name, type)
}
