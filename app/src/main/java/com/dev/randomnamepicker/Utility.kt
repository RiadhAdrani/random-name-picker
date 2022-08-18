package com.dev.randomnamepicker

import android.content.Context
import android.widget.Toast
import com.dev.randomnamepicker.models.ItemList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val SP = "SHARED_PREFERENCES"
const val ITEMS = "ITEMS"

fun showToast(text: String, context: Context) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

fun saveItems(items: ArrayList<ItemList>, context: Context) {
    try {
        val sharedPreference = context.getSharedPreferences(SP, Context.MODE_PRIVATE)

        val gson = Gson()
        val input = gson.toJson(items.map { item -> item.toMap() })

        val editor = sharedPreference.edit()
        editor.putString(ITEMS, input)
        editor.apply()
    }catch (e: Exception){

    }

}

fun getItems(context: Context): ArrayList<ItemList> {
    try {
        val sharedPreference = context.getSharedPreferences(SP, Context.MODE_PRIVATE)
            ?: return ArrayList()

        val stored = sharedPreference.getString(ITEMS, "") ?: return ArrayList()

        val type = Gson().fromJson<List<Map<String, Any>>>(stored)

        return ArrayList(type.map { item -> ItemList.fromMap(item) })
    } catch (e: Exception) {
        return ArrayList()
    }
}