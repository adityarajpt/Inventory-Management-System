package classes

data class Borrower(private val email : String)
{
    var itemsBorrowed : MutableList<BorrowedItem> = mutableListOf()
    var name : String? = null
}