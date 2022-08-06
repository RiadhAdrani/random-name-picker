package com.dev.randomnamepicker.models

import java.util.*
import kotlin.collections.ArrayList

data class ItemList(
    var name: String,
    var id: String = UUID.randomUUID().toString(),
    var items: ArrayList<Item> = ArrayList(),
    var color: Number = 0,
    var selectedItems: ArrayList<String> = ArrayList()
) {
    fun toMap(): Map<String, Any> {
        return mapOf(
            "id" to id,
            "name" to name,
            "items" to items.map { item -> item.toMap() },
            "color" to color,
            "selectedItems" to selectedItems
        )
    }

    companion object {
        fun fromMap(map: Map<String, Any>): ItemList {

            var id = UUID.randomUUID().toString()
            var name = "Nameless List"
            var items = ArrayList<Item>()
            var color = 0
            var selectedItems = ArrayList<String>()

            if (map.containsKey("id")) {
                id = map["id"] as String
            }
            if (map.containsKey("name")) {
                name = map["name"] as String
            }
            if (map.containsKey("items")) {
                val raw = map["items"] as List<Map<String, Any>>
                items = ArrayList(raw.map { item -> Item.fromMap(item) })
            }
            if (map.containsKey("color")) {
                color = (map["color"] as Double).toInt()
            }

            if (map.containsKey("selectedItems")) {
                val raw = map["selectedItems"] as ArrayList<String>
                selectedItems = ArrayList(raw)
            }

            return ItemList(name, id, items, color, selectedItems)
        }
    }
}