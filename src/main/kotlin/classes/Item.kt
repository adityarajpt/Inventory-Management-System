package classes


data class Item(val name :String,
                val description: String,
                var category: Category? = null,
)
