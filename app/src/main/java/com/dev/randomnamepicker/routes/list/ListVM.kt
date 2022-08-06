package com.dev.randomnamepicker.routes.list

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dev.randomnamepicker.models.Item
import com.dev.randomnamepicker.models.ItemList
import com.dev.randomnamepicker.saveItems
import com.dev.randomnamepicker.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor() : ViewModel() {

    companion object{
        const val DONE = "All items were picked"
        const val CLICK_TO_PICK = "Click to Pick"
    }

    var listOfLists = mutableStateListOf<ItemList>()

    var list by mutableStateOf(ItemList("Nameless List"))

    var items = mutableStateListOf<Item>()
    var selected = mutableStateListOf<String>()

    var field by mutableStateOf("")
    var showRaffle by mutableStateOf(false)

    fun addItem(context: Context) {
        if (field.isBlank()){
            showToast("Name cannot be empty !", context)
            return
        }
        if (field.trim().lowercase() == CLICK_TO_PICK.lowercase()){
            showToast("The name you chose is reserved.", context)
            return
        }

        if (field.trim().lowercase() == DONE.lowercase()){
            showToast("The name you chose is reserved.", context)
            return
        }

        val newItem = Item(content = field)
        items.add(newItem)
        list.items.add(newItem)

        field = ""
        save(context)
    }

    fun removeItem(id: String, context: Context) {
        items.removeIf { item -> item.id == id }
        selected.remove(id)

        save(context)
    }

    fun isSelected(id: String): Boolean {
        return selected.contains(id)
    }

    fun toggleSelected(id: String, context: Context) {
        if (isSelected(id)) {
            selected.remove(id)
        } else {
            selected.add(id)
        }

        save(context)
    }

    private fun save(context: Context) {

        list.selectedItems = ArrayList(selected)
        list.items = ArrayList(items)

        listOfLists.removeIf { item -> item.id == list.id }
        listOfLists.add(0, list)

        saveItems(ArrayList(listOfLists), context)
    }
}