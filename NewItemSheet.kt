import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todolisttutorial.databinding.FragmentNewItemSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class NewItemSheet(var item: Item?) : BottomSheetDialogFragment()
{
    private lateinit var binding: FragmentNewItemSheetBinding
    private lateinit var itemViewModel: ItemViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (item != null)
        {
            binding.itemTitle.text = "Edit Item"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(item!!.name)
            binding.desc.text = editable.newEditable(item!!.desc)
            if(item!!.dueTime != null){
                dueTime = item!!.dueTime!!
                updateTimeButtonText()
            }
        }
        else
        {
            binding.itemTitle.text = "New Task"
        }

        itemViewModel = ViewModelProvider(activity).get(ItemViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.timePickerButton.setOnClickListener {
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if(dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Task Due")
        dialog.show()

    }

    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewItemSheetBinding.inflate(inflater,container,false)
        return binding.root
    }


    private fun saveAction()
    {
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        if(item == null)
        {
            val newTask = Item(name,desc,dueTime,null)
            itemViewModel.addTaskItem(newTask)
        }
        else
        {
            itemViewModel.updateItem(item!!.id, name, desc, dueTime)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}
tar -xzf android-studio-2022.3.1.19-linux.tar.gz
cd android-studio-2022.3.1.19-linux
./configure
make
sudo make install
