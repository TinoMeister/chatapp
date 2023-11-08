package pt.ipca.whaza.models

import com.google.firebase.Timestamp

class Chat(id: Int, userid: Int, date: Timestamp, name: String) {
    val id: Int
    val userid : Int
    val date: Timestamp
    val name: String

    init {
        this.id = id
        this.userid = userid
        this.date = date
        this.name = name
    }

}