package pt.ipca.whaza.models

import com.google.firebase.Timestamp

class Message(id: Int, chatid: Int, userid: Int, date: Timestamp, body: String, messagetypeid: Int) {
    val id: Int
    val chatid: Int
    val userid: Int
    val date: Timestamp
    val body: String
    val messagetypeid: Int

    init {
        this.id = id
        this.chatid = chatid
        this.userid = userid
        this.date = date
        this.body = body
        this.messagetypeid = messagetypeid
    }
}