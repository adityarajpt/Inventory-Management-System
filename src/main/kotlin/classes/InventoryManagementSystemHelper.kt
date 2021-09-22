package classes

import java.util.*

class InventoryManagementSystemHelper : InventoryManagementSystem() {


    fun showAllCategories(sc : Scanner) {
        val categoriesList = super.showAllCategories()
        val categoriesMap = categoriesList.associateWith {category -> items.filter{it.key.category == category}.values.sum()}
        println("Category   :    Quantity")
        println("------------------------")
        for((key,value) in categoriesMap.entries){
            println("${key.name}    :    $value")
        }
    }

    fun showAllItems(sc : Scanner){
        val itemsMap = super.showAllItems()
        println("Items   :    Quantity")
        println("---------------------")
        for( (key,value) in itemsMap.entries){
            println("${key.name}  :  $value")
        }
    }

    fun addItem(sc : Scanner){
        println("Enter the name of item you want to create")
        print("Name : ")
        val name = sc.nextLine()
        println("Enter the description of category")
        print("Description : ")
        val description = sc.nextLine()


        println("Choose the category of Item : ")
        println("1 : Choose from existing categories")
        println("Any Other Number : None")

        val category: Category? = when(sc.nextLine().toInt()){
            1 -> chooseCategoryForItem(sc)
            else -> {
                null
            }
        }

        super.addItem(Item(name, description, category))
        println("Item Added Successfully !!!")
    }

    fun removeItem(sc : Scanner){
        println("Enter the index of Item from list below that you want to remove")
        println("")
        items.keys.mapIndexed{
            idx, value -> println("$idx : ${value.name}")
        }
        println("Index to delete : ")
        val index = sc.nextLine().toInt()
        super.removeItem(items.keys.toList()[index])

        println("Item Removed Successfully !!!")
    }

    fun addCategory(sc: Scanner){
        println("Enter the name of category you want to create")
        print("Name : ")
        val name = sc.nextLine()
        println("Enter the description of category")
        print("Description : ")
        val description = sc.nextLine()

        val category = Category(name, description)
        super.addCategory(category)

        println("Category Added Successfully !!!")
    }

    fun removeCategory(sc : Scanner){
        println("Enter the index of Item from list below that you want to remove")
        println("")
        categories.mapIndexed{
                idx, value -> println("$idx : ${value.name}")
        }

        println("Index to delete : ")
        val index = sc.nextLine().toInt()
        super.removeCategory(categories[index])

        println("Category Removed Successfully !!!")
    }

    fun borrowItem(sc : Scanner){
        println("")
        print("Enter you Name : ")
        val name = sc.nextLine()
        println("")
        print("Enter you email : ")
        val email = sc.nextLine()

        val borrower = Borrower(email)
        borrower.name = name

        println("Enter the index of Item from list below that you want to borrow")
        println("")
        items.keys.mapIndexed{
                idx, value -> println("$idx : ${value.name}")
        }
        println("Index to borrow : ")
        val index = sc.nextLine().toInt()

        val selectedItem = items.keys.toList()[index]
        super.borrowItem(borrower, selectedItem)

        println("Item Borrowing Successful !!!")
    }

    fun returnItem(sc : Scanner){
        println("Enter your email address")
        println(" Email : ")
        val email = sc.nextLine()
        var tempBorrower = Borrower(email)

        for(i in borrowers){
            if(tempBorrower == i){
                tempBorrower = i
                break
            }
        }

        println("Select the index of item to be returned : ")
        tempBorrower.itemsBorrowed.mapIndexed{
            idx, value -> println("$idx    :      ${value.item.name}      :     ${value.date}")
        }
        val index = sc.nextLine().toInt()

        val response = super.returnItem(tempBorrower, tempBorrower.itemsBorrowed[index])

        if (response) println(" Items returned successfully !!!") else println("Some error occurred, try again")
    }

    fun showBorrowingHistory(sc : Scanner){
        val borrowingHistory = super.showBorrowingHistory()
        println("Borrower Name       :     Item Name     :   Date   :   Status")
        for(entry in borrowingHistory){
            println("${entry.first.item.name}       :     ${entry.second.name}     :   ${entry.first.date}   :  ${if(entry.first.returned) "Returned" else "Not Returned"}")
        }
    }

    private fun chooseCategoryForItem(sc : Scanner) : Category?{
        println("Choose the index of category :")
        categories.mapIndexed{
            idx, value -> println("$idx   :   ${value.name}")
        }
        val choice = sc.nextLine().toInt()
        if (choice < categories.size){
            return categories[choice]
        }
        return null
    }

    fun showBorrowers(sc : Scanner){
        val borrowersInfo = super.showBorrowers()

        for((first, second) in borrowersInfo){
            println("$first   :    $second")
        }
    }
}