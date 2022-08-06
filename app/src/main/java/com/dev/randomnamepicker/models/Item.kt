package com.dev.randomnamepicker.models

import java.util.*

data class Item(var id: String = UUID.randomUUID().toString(), var content: String) {

    fun toMap(): Map<String, String> {
        return mapOf("id" to id, "content" to content)
    }

    companion object {
        fun fromMap(map: Map<String, Any>): Item {
            val id =
                (if (map.containsKey("id")) map["id"]
                else UUID.randomUUID().toString())
                        as String
            val content =
                (if (map.containsKey("content")) map["content"]
                else "")
                        as String

            return Item(id, content)
        }
    }
}