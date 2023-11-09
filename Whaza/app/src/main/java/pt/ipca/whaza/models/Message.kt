package pt.ipca.whaza.models

import java.util.Date

data class Message(var id: String? = "",
                   var chatid: String? = "",
                   var userid: String? = "",
                   var date: Date? = null,
                   var body: String? = "",
                   var messagetypeid: Int? = 0)