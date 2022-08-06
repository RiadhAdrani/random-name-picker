package com.dev.randomnamepicker.routes.home

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.dev.randomnamepicker.getItems
import com.dev.randomnamepicker.models.ItemList
import com.dev.randomnamepicker.navigation.Route
import com.dev.randomnamepicker.saveItems
import com.dev.randomnamepicker.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {
    var items = mutableStateListOf<ItemList>()
    var showModal by mutableStateOf(false)

    fun retrieveItems(context: Context) {
        items = SnapshotStateList()
        items.addAll(getItems(context))
    }

    fun addItem(text: String, context: Context, navController: NavController) {
        if (text.isBlank()) {
            showToast("List name cannot be empty !", context)
            return
        }

        val newItem = ItemList(name = text, color = 0)

        items.add(newItem)
        toggleModal(false)
        saveItems(ArrayList(items), context)

        navController.navigate(Route.List.path + "/${newItem.id}")
    }

    fun removeItem(id: String, context: Context) {
        items.removeIf { it.id == id }
        saveItems(ArrayList(items), context)
    }

    fun toggleModal(value: Boolean?) {
        showModal = value ?: !showModal
    }
}