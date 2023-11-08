package pt.ipca.whaza.models

import com.google.firebase.Timestamp

class Chat(id: String, userids: List<String>, date: Timestamp, name: String) {
    val id: String
    val userids : List<String>
    val date: Timestamp
    val name: String

    init {
        this.id = id
        this.userids = userids
        this.date = date
        this.name = name
    }

}