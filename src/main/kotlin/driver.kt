import classes.InventoryManagementSystemHelper
import java.util.*

fun main() {

    // TODO : Sanitize user-input (ArrayIndex out of bound and input exceptions will occur for now)

    val sc = Scanner(System.`in`)
    val inventoryManagementSystemHelper = InventoryManagementSystemHelper()
    println("---------------------------- Inventory Management System ------------------------------")
    while(true){
        println("")
        println("1 : Show all Items")
        println("2 : Show all Categories")
        println("3 : Add a category")
        println("4 : Add a item")
        println("5 : Remove a item")
        println("6 : Remove a category")
        println("7 : Borrow a item")
        println("8 : Return a item")
        println("9 : Show borrowing history")
        println("10 : Show Borrowers")
        println("99 : Exit")
        print("Enter a number : ")
        when(sc.nextLine().toInt()){
            1 -> inventoryManagementSystemHelper.showAllItems(sc)
            2 -> inventoryManagementSystemHelper.showAllCategories(sc)
            3 -> inventoryManagementSystemHelper.addCategory(sc)
            4 -> inventoryManagementSystemHelper.addItem(sc)
            5 -> inventoryManagementSystemHelper.removeItem(sc)
            6 -> inventoryManagementSystemHelper.removeCategory(sc)
            7 -> inventoryManagementSystemHelper.borrowItem(sc)
            8 -> inventoryManagementSystemHelper.returnItem(sc)
            9 -> inventoryManagementSystemHelper.showBorrowingHistory(sc)
            10 -> inventoryManagementSystemHelper.showBorrowers(sc)
            99 -> break
            else -> {
                println("Invalid Choice, Choose again")
            }
        }
    }

    sc.close()

}
