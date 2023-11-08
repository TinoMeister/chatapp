package pt.ipca.whaza.models

import com.google.firebase.Timestamp

class Message(id: String, chatid: String, userid: String, date: Timestamp, body: String, messagetypeid: String) {
    val id: String
    val chatid: String
    val userid: String
    val date: Timestamp
    val body: String
    val messagetypeid: String

    init {
        this.id = id
        this.chatid = chatid
        this.userid = userid
        this.date = date
        this.body = body
        this.messagetypeid = messagetypeid
    }
}