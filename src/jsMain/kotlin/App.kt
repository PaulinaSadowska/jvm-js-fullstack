import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.*
import react.dom.h1
import react.dom.li
import react.dom.ul

private val scope = MainScope()

val app = functionComponent<Props> { _ ->
    var shoppingList by useState(emptyList<ShoppingListItem>())

    useEffectOnce {
        scope.launch {
            shoppingList = getShoppingList()
        }
    }

    h1{
        +"Full-stack shopping list"
    }
    ul {
        shoppingList.sortedByDescending { it.priority }.forEach {item ->
            li{
                key = item.toString()
                +"[${item.priority}] ${item.desc}"
            }
        }
    }

}