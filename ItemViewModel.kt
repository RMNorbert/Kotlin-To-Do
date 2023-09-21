import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class ItemViewModel: ViewModel()
{
    var items = MutableLiveData<MutableList<Item>>()

    init {
        items.value = mutableListOf()
    }

    fun addItem(newItem: Item)
    {
        val list = items.value
        list!!.add(newItem)
        items.postValue(list)
    }

    fun updateItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
    {
        val list = items.value
        val item = list!!.find { it.id == id }!!
        item.name = name
        item.desc = desc
        item.dueTime = dueTime
        items.postValue(list)
    }

    fun setCompleted(item: Item)
    {
        val list = items.value
        val item = list!!.find { it.id == item.id }!!
        if (item.completedDate == null)
            item.completedDate = LocalDate.now()
        items.postValue(list)
    }
}
