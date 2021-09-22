package classes

import java.lang.IllegalStateException
import java.time.LocalDateTime

open class InventoryManagementSystem {
    protected val items : MutableMap<Item, Int> = mutableMapOf()
    private val borrowedItemsAndBorrowers : MutableList<Pair<BorrowedItem, Borrower>> = mutableListOf()
    protected val borrowers : MutableList<Borrower> = mutableListOf()
    protected val categories : MutableList<Category> = mutableListOf()

    init {
        val category1 = Category("Furniture", "Some Wooden articles")
        val category2 = Category("Books", "Books, Novels, Comics")
        addCategory(category1)
        addCategory(category2)

        val item1 = Item("First Item", "Desk", category1)
        val item2 = Item("Second Item", "Chair", category1)
        val item3 = Item("Clean Code", "A book", category2)

        addItem(item1)
        addItem(item2)
        addItem(item3)
    }

    fun showAllItems(): MutableMap<Item, Int> {
        return items
    }

    fun addItem(item : Item){
        val currentCount = items[item] ?: 0
        items[item] = currentCount + 1

        // In case item category not already present in categories list
        item.category?.let { addCategory(it) }
    }

    fun removeItem(item: Item){
        val currentCount = items[item]

        if(currentCount != null){
            if(currentCount == 1){
                items.remove(item)
            }
            else{
                items[item] = currentCount - 1
            }
        }
        else{
            throw IllegalStateException("Illegal Item  : ${item.name}")
        }
    }

    fun showAllCategories(): MutableList<Category> {
        return categories
    }

    fun addCategory(category: Category){
        if(category !in categories){
            categories.add(category)
        }
    }

    fun removeCategory(category: Category){
        if(category in categories){
            categories.remove(category)

            //Removing all items associated with this category
            for(item in items.keys){
                if(item.category == category){
                    item.category = null
                }
            }
        }
    }

    fun borrowItem(borrower: Borrower, item: Item){
        if (item in items){
            val borrowedItem = BorrowedItem(item, LocalDateTime.now())
            items.remove(item)
            borrower.itemsBorrowed.add(borrowedItem)
            borrowers.add(borrower)
            borrowedItemsAndBorrowers.add(Pair(borrowedItem, borrower))
        }
        else{
            //Throw Exception Here
            throw IllegalStateException("Illegal Items")
        }
    }

    fun returnItem(borrower: Borrower, borrowedItem: BorrowedItem) : Boolean{
        if (borrowedItem in borrower.itemsBorrowed){
            borrower.itemsBorrowed.remove(borrowedItem)
            addItem(borrowedItem.item)

            if (borrower.itemsBorrowed.size == 0){
                borrowers.remove(borrower)
            }

            borrowedItem.returned = true

            return true
        }
        return false
    }

    fun showBorrowingHistory(): MutableList<Pair<BorrowedItem, Borrower>> {
        return borrowedItemsAndBorrowers
    }

    fun showBorrowers(): Map<String?, Int> {
        return borrowedItemsAndBorrowers
            .filter { !it.first.returned }
            .map { value -> value.second.name }
            .groupingBy { it }.eachCount()
    }
}

//Searching all three
