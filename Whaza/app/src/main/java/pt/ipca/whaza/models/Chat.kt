package pt.ipca.whaza.models

import java.util.Date

data class Chat(var id: String? = "",
                var userids: MutableList<String> = mutableListOf(),
                var date: Date? = null,
                var name: String = "")