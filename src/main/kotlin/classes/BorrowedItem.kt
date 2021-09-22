package classes

import java.time.LocalDateTime


data class BorrowedItem(val item: Item, val date: LocalDateTime){
    var returned = false
}