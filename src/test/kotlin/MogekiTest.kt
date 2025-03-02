import com.mongodb.client.model.Filters
import gg.levely.system.mogeki.Component
import gg.levely.system.mogeki.ComponentCodec
import gg.levely.system.mogeki.Key
import gg.levely.system.mogeki.Mogeki
import gg.levely.system.mogeki.key
import org.bson.conversions.Bson

class MogekiTest {

    lateinit var mogeki: Mogeki

    val NAME_COMPONENT = key<NameComponent>("name")
    val TYPE_COMPONENT = key<TypeComponent>("type")

    init {
        val componentCodecRepository = mogeki.componentCodecRepository
        componentCodecRepository.register(NAME_COMPONENT, NameComponentCodec())

        val mogekiCollection = mogeki.getMogekiCollection("test")
        val nameComponent = mogekiCollection.getComponent(TYPE_COMPONENT.filter("foo"), NAME_COMPONENT)

        println(nameComponent)


    }
}

class NameComponent(
    val name: String,
) : Component


class TypeComponent(
    val type: String,
) : Component

class NameComponentCodec : ComponentCodec<NameComponent, String> {

    override fun marshal(component: NameComponent): String {
        return component.name
    }

    override fun unMarshal(output: String): NameComponent {
        return NameComponent(output)
    }
}

fun Key<TypeComponent>.filter(type: String): Bson {
    return Filters.eq(name, type)
}